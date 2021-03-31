package com.mycovtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotIssuedTTNNumberException extends RuntimeException {

    public NotIssuedTTNNumberException() {
        super("The TTN Number is not an issued TTN Number");
    }
}
