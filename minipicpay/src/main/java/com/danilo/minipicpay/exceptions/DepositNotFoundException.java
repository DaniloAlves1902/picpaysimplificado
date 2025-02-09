package com.danilo.minipicpay.exceptions;

public class DepositNotFoundException extends RuntimeException {
    public DepositNotFoundException(String message) {
        super(message);
    }
}
