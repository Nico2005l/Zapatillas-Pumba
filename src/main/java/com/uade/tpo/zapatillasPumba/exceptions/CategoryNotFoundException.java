package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "La categoría no existe")
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("La categoría no existe");
    }
}