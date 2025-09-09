package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El rol de usuario es inválido")
public class InvalidUserRoleException extends RuntimeException {
    public InvalidUserRoleException() {
        super("El rol de usuario es inválido");
    }
}