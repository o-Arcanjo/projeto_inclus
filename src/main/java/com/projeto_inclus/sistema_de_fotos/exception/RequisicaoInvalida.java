package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class RequisicaoInvalida extends CustomBaseException {

    public RequisicaoInvalida(String message) { super(message); }

    public RequisicaoInvalida(String message, Throwable cause) { super(message, cause); }

    @Override
    public HttpStatus getHttpStatus() { return HttpStatus.BAD_REQUEST; }

    @Override
    public ErrorTypeEnum getErrorType() { return ErrorTypeEnum.REQUISICAO_INVALIDA; }
}
