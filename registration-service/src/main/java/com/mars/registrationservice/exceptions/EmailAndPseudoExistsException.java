package com.mars.registrationservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class EmailAndPseudoExistsException extends RuntimeException {
    public EmailAndPseudoExistsException(String s) {
        super(s);
    }
}
