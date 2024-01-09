package com.truecaller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactAlreadyExistsException extends  RuntimeException{
    public ContactAlreadyExistsException(String message) {
        super(message);
    }
    public ContactAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
