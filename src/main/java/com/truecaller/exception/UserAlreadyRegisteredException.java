package com.truecaller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyRegisteredException extends Exception {

    public UserAlreadyRegisteredException(String student) {
        super(String.format("user with name %s already registered .", student));
    }
}
