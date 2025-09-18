package com.projeto_inclus.sistema_de_fotos.exception;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public interface ICustomBaseException {
    HttpStatus getHttpStatus();
    ErrorTypeEnum getErrorType();
}
