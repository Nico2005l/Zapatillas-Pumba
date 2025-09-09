package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "No tienes permisos para realizar esta acción")
public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException() {
        super("No tienes permisos para realizar esta acción");
    }
}