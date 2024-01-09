package com.truecaller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MobileIdNotFoundException extends  RuntimeException {

    public MobileIdNotFoundException(String message) {
        super(message);
    }
    public MobileIdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
