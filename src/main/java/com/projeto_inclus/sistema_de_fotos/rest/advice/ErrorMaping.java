package com.projeto_inclus.sistema_de_fotos.rest.advice;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import com.projeto_inclus.sistema_de_fotos.exception.ICustomBaseException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public final class ErrorMaping implements ICustomBaseException {
    private final ErrorTypeEnum errorType;
    private final HttpStatus httpStatus;
}
