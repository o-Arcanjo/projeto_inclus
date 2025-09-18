package com.projeto_inclus.sistema_de_fotos.rest.advice;

import com.projeto_inclus.sistema_de_fotos.enums.ErrorTypeEnum;
import org.springframework.http.HttpStatus;

public class ErrorMapingFactory {
    public static ErrorMaping instanciarErrorMaping(ErrorTypeEnum errorTypeEnum, HttpStatus httpStatus){
        return new ErrorMaping(errorTypeEnum, httpStatus);
    }
}
