package com.uade.tpo.zapatillasPumba.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El producto con el ID especificado no fue encontrado.")
public class DiscountProductNotFoundException extends Exception {

}
