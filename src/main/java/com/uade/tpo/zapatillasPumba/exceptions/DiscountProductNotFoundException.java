package com.uade.tpo.zapatillasPumba.exceptions;

public class DiscountProductNotFoundException extends RuntimeException {
    public DiscountProductNotFoundException(String message) {
        super(message);
    }

    public DiscountProductNotFoundException() {
        super("No se encontró el producto asociado al descuento.");
    }
}