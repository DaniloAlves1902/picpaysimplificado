package com.danilo.minipicpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WithdrawNotFoundException extends RuntimeException {
    public WithdrawNotFoundException(String message) {
        super(message);
    }
}
