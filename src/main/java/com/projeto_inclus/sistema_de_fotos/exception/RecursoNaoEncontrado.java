package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class RecursoNaoEncontrado extends CustomBaseException {

    public RecursoNaoEncontrado(String message) {
        super(message);
    }

    public RecursoNaoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.RECURSO_NAO_ENCONTRADO;
    }
}
