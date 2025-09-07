package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Los datos del producto son inválidos")
public class InvalidProductDataException extends RuntimeException {
    public InvalidProductDataException() {
        super("Los datos del producto son inválidos");
    }
    
    public InvalidProductDataException(String message) {
        super(message);
    }
}