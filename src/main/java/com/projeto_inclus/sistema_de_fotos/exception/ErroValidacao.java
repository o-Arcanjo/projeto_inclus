package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class ErroValidacao extends CustomBaseException {

    public ErroValidacao(String message) { super(message); }

    public ErroValidacao(String message, Throwable cause) { super(message, cause); }

    @Override
    public HttpStatus getHttpStatus() { return HttpStatus.BAD_REQUEST; }

    @Override
    public ErrorTypeEnum getErrorType() { return ErrorTypeEnum.ERRO_DE_VALIDACAO; }

    @Override
    public String getLocalizedMessage() {
        return super.getMessage();
    }
}
