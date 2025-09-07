package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "El producto ya existe")
public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("El producto ya existe");
    }
}