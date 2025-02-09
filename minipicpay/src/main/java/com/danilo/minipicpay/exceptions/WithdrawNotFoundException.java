package com.danilo.minipicpay.exceptions;

public class WithdrawNotFoundException extends RuntimeException {
    public WithdrawNotFoundException(String message) {
        super(message);
    }
}
