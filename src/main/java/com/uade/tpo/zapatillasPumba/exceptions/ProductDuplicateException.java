package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductDuplicateException extends RuntimeException {
    public ProductDuplicateException(String message) {
        super(message);
    }
}
