package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public abstract class CustomBaseException extends RuntimeException implements ICustomBaseException {
    public CustomBaseException(String message) {
        super(message);
    }

    public CustomBaseException(String message, Throwable cause){
        super(message, cause);
    }

    public CustomBaseException(){}

    @Override
    public abstract HttpStatus getHttpStatus();

    @Override
    public abstract ErrorTypeEnum getErrorType();
}
