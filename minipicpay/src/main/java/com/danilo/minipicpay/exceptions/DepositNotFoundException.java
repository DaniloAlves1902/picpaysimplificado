package com.danilo.minipicpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepositNotFoundException extends RuntimeException {
    public DepositNotFoundException(String message) {
        super(message);
    }
}
