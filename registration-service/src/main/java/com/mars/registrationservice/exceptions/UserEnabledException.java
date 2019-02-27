package com.mars.registrationservice.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class UserEnabledException  extends RuntimeException {
    public UserEnabledException(String s) {
        super(s);
    }
}
