package com.danilo.minipicpay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDepositAmountException extends RuntimeException {
    public InvalidDepositAmountException(String message) {
        super(message);
    }
}
