package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class EstadoInvalidoException extends CustomBaseException {
    public EstadoInvalidoException(String message) {
        super(message);
    }

    public EstadoInvalidoException(String message, Throwable cause){
        super(message,cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    @Override
    public ErrorTypeEnum getErrorType() {
        return ErrorTypeEnum.ESTADO_INVALIDO;
    }
}
