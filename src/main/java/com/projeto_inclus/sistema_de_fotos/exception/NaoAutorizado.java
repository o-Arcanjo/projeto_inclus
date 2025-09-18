package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class NaoAutorizado extends CustomBaseException {

    public NaoAutorizado(String message) { super(message); }

    public NaoAutorizado(String message, Throwable cause) { super(message, cause); }

    @Override
    public HttpStatus getHttpStatus() { return HttpStatus.FORBIDDEN; }

    @Override
    public ErrorTypeEnum getErrorType() { return ErrorTypeEnum.NAO_AUTORIZADO; }

    @Override
    public String getLocalizedMessage() {
        return super.getMessage();
    }
}
