package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class Conflito extends CustomBaseException {

    public Conflito(String message) { super(message); }

    public Conflito(String message, Throwable cause) { super(message, cause); }

    @Override
    public HttpStatus getHttpStatus() { return HttpStatus.CONFLICT; }

    @Override
    public ErrorTypeEnum getErrorType() { return ErrorTypeEnum.CONFLITO; }
}
