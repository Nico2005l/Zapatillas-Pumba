package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "El descuento no existe")
public class DiscountNotFoundException extends RuntimeException {
    public DiscountNotFoundException() {
        super("El descuento no existe");
    }
}
