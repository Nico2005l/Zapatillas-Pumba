package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryHasProductsException extends RuntimeException {
    public CategoryHasProductsException() {
        super("No se puede eliminar la categoría porque tiene productos asociados");
    }

    public CategoryHasProductsException(String message) {
        super(message);
    }
}