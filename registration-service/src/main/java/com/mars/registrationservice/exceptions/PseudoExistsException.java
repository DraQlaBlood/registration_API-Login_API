package com.mars.registrationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class PseudoExistsException extends RuntimeException {
    public PseudoExistsException(String s) {
        super(s);
    }
}
