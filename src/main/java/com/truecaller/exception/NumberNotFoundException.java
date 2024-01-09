package com.truecaller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class NumberNotFoundException extends  RuntimeException {
        public NumberNotFoundException(String message) {
            super(message);
        }
        public NumberNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

    }

