package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El valor del descuento debe estar entre 0 y 1.00.")
public class DiscountOutOfRangeException extends RuntimeException {
    public DiscountOutOfRangeException() {
        super("El valor del descuento debe estar entre 0 y 1.00.");
    }
}
