package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "La categoría padre es inválida")
public class InvalidCategoryParentException extends RuntimeException {
    public InvalidCategoryParentException() {
        super("La categoría padre es inválida");
    }
}