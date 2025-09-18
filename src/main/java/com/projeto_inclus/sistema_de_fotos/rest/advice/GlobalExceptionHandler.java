package com.projeto_inclus.sistema_de_fotos.rest.advice;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import com.projeto_inclus.sistema_de_fotos.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Map<Class<? extends Exception>, ErrorMaping> errorStatusMap = new HashMap<>();

    static {
        // Erros de requisição inválida - 400
        addMapping(HttpRequestMethodNotSupportedException.class,
                ErrorTypeEnum.REQUISICAO_INVALIDA, HttpStatus.BAD_REQUEST);
        addMapping(HttpMediaTypeNotSupportedException.class,
                ErrorTypeEnum.REQUISICAO_INVALIDA, HttpStatus.BAD_REQUEST);
        addMapping(HttpMediaTypeNotAcceptableException.class,
                ErrorTypeEnum.REQUISICAO_INVALIDA, HttpStatus.BAD_REQUEST);
        addMapping(MissingServletRequestParameterException.class,
                ErrorTypeEnum.REQUISICAO_INVALIDA, HttpStatus.BAD_REQUEST);
        addMapping(MissingServletRequestPartException.class,
                ErrorTypeEnum.REQUISICAO_INVALIDA, HttpStatus.BAD_REQUEST);
        addMapping(HttpMessageNotReadableException.class,
                ErrorTypeEnum.REQUISICAO_INVALIDA, HttpStatus.BAD_REQUEST);

        // Erros de validação - 400
        addMapping(MethodArgumentNotValidException.class,
                ErrorTypeEnum.ERRO_DE_VALIDACAO, HttpStatus.BAD_REQUEST);
        addMapping(BindException.class,
                ErrorTypeEnum.ERRO_DE_VALIDACAO, HttpStatus.BAD_REQUEST);
        addMapping(MaxUploadSizeExceededException.class,
                ErrorTypeEnum.ERRO_DE_VALIDACAO, HttpStatus.BAD_REQUEST);

        // Recursos não encontrados - 404
        addMapping(NoResourceFoundException.class,
                ErrorTypeEnum.RECURSO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);
        addMapping(EmptyResultDataAccessException.class,
                ErrorTypeEnum.RECURSO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND);

        // Conflitos - 409
        addMapping(DataIntegrityViolationException.class,
                ErrorTypeEnum.CONFLITO, HttpStatus.CONFLICT);

        // Serviço indisponível - 503
        addMapping(AsyncRequestTimeoutException.class,
                ErrorTypeEnum.SERVICO_INDISPONIVEL, HttpStatus.SERVICE_UNAVAILABLE);

        // Erros de autenticação - 401
        addMapping(BadCredentialsException.class,
                ErrorTypeEnum.NAO_AUTENTICADO, HttpStatus.UNAUTHORIZED);
        addMapping(AuthenticationCredentialsNotFoundException.class,
                ErrorTypeEnum.NAO_AUTENTICADO, HttpStatus.UNAUTHORIZED);
        addMapping(DisabledException.class,
                ErrorTypeEnum.NAO_AUTENTICADO, HttpStatus.UNAUTHORIZED);
        addMapping(LockedException.class,
                ErrorTypeEnum.NAO_AUTENTICADO, HttpStatus.UNAUTHORIZED);
        addMapping(UsernameNotFoundException.class,
                ErrorTypeEnum.NAO_AUTENTICADO, HttpStatus.UNAUTHORIZED);

        // Erros de autorização - 403
        addMapping(AccessDeniedException.class,
                ErrorTypeEnum.NAO_AUTORIZADO, HttpStatus.FORBIDDEN);


        // Erros de Estado Inválido - 422
        addMapping(IllegalArgumentException.class,
                ErrorTypeEnum.ESTADO_INVALIDO, HttpStatus.UNPROCESSABLE_ENTITY);
        addMapping(IllegalStateException.class,
                ErrorTypeEnum.ESTADO_INVALIDO, HttpStatus.UNPROCESSABLE_ENTITY);

    }

    private static void addMapping(
            Class<? extends Exception> exceptionClass,
            ErrorTypeEnum errorTypeEnum,
            HttpStatus httpStatus) {
        errorStatusMap.put(exceptionClass, ErrorMapingFactory.instanciarErrorMaping(errorTypeEnum, httpStatus));
    }

    private static CustomBaseException getErrorMapping(Exception ex) {
        return errorStatusMap.getOrDefault(ex.getClass(),
                ErrorMapingFactory.instanciarErrorMaping(ErrorTypeEnum.ERRO_INESPERADO, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private String stackTraceToString(CustomBaseException ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }

    private ProblemDetail buildProblemDetail(CustomBaseException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                ex.getHttpStatus(), ex.getLocalizedMessage());
        problemDetail.setType(URI.create(ex.getErrorType().getUri()));
        problemDetail.setProperty("trace", stackTraceToString(ex));
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    private ProblemDetail buildProblemDetailConjunct(BindingResult bindingResult){
        var fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 1) {
            Map<String, String> errors = new HashMap<>();
            fieldErrors.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

            ProblemDetail problemDetail = buildProblemDetail(
                    new ErroValidacao("Vários erros de validação")
            );
            problemDetail.setProperty("erros", errors);
            return problemDetail;
        } else {
            String message = fieldErrors.isEmpty() ? "Erro de validação" : fieldErrors.get(0).getDefaultMessage();
            return buildProblemDetail(
                    new ErroValidacao(message)
            );
        }
    }

    @ExceptionHandler({
            Exception.class,
            IllegalArgumentException.class,
            DataIntegrityViolationException.class,
            BadCredentialsException.class
    })
    public ProblemDetail handleException(Exception ex){
        return buildProblemDetail(getErrorMapping(ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex){
        return buildProblemDetailConjunct(ex.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    public ProblemDetail handleBindException(BindException ex){
        return buildProblemDetailConjunct(ex.getBindingResult());
    }

    @ExceptionHandler(EstadoInvalidoException.class)
    public ProblemDetail handleEstadoInvalidoException(EstadoInvalidoException ex) {
        return buildProblemDetail(ex);
    }

    @ExceptionHandler(RecursoNaoEncontrado.class)
    public ProblemDetail handleRecursoNaoEncontrado(RecursoNaoEncontrado ex){
        return buildProblemDetail(ex);
    }

    @ExceptionHandler(NaoAutorizado.class)
    public ProblemDetail handleNaoAutorizado(NaoAutorizado ex){
        return buildProblemDetail(ex);
    }

    @ExceptionHandler(RequisicaoInvalida.class)
    public ProblemDetail handleRequisicaoInvalida(RequisicaoInvalida ex){
        return buildProblemDetail(ex);
    }

    @ExceptionHandler(ErroValidacao.class)
    public ProblemDetail handleErroValidacao(ErroValidacao ex){
        return buildProblemDetail(ex);
    }

    @ExceptionHandler(Conflito.class)
    public ProblemDetail handleConflito(Conflito ex){
        return buildProblemDetail(ex);
    }

    @ExceptionHandler(NaoAutenticado.class)
    public ProblemDetail handleNaoAutenticado(NaoAutenticado ex){
        return buildProblemDetail(ex);
    }

    }