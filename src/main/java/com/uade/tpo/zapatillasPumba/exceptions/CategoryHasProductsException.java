package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "La categoría tiene productos asociados y no puede eliminarse")
public class CategoryHasProductsException extends RuntimeException {
    public CategoryHasProductsException() {
        super("La categoría tiene productos asociados y no puede eliminarse");
    }
}