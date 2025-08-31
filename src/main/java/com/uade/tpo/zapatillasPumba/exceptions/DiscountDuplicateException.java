package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DiscountDuplicateException extends RuntimeException {

    public DiscountDuplicateException(String message) {
        super(message);
    }

}
