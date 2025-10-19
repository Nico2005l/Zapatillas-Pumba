package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidCategoryParentException extends RuntimeException {
    public InvalidCategoryParentException(String message) {
        super(message);
    }
}