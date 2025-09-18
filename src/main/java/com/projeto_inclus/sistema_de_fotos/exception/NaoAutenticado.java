package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class NaoAutenticado extends CustomBaseException {

    public NaoAutenticado(String message) { super(message); }

    public NaoAutenticado(String message, Throwable cause) { super(message, cause); }

    @Override
    public HttpStatus getHttpStatus() { return HttpStatus.UNAUTHORIZED; }

    @Override
    public ErrorTypeEnum getErrorType() { return ErrorTypeEnum.NAO_AUTENTICADO; }

    @Override
    public String getLocalizedMessage() {
        return super.getMessage();
    }
}
