package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "El ítem de la orden no existe")
public class OrderItemNotFoundException extends RuntimeException {
    public OrderItemNotFoundException() {
        super("El ítem de la orden no existe");
    }
}