package com.danilo.minipicpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidWithdrawAmountException extends RuntimeException {
    public InvalidWithdrawAmountException(String message) {
        super(message);
    }
}
