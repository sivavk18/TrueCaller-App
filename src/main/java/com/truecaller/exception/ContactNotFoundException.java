package com.truecaller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends  RuntimeException {
    public ContactNotFoundException(String message) {
        super(message);
    }
    public ContactNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
