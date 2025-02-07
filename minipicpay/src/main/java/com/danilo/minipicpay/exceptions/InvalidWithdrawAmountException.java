package com.danilo.minipicpay.exceptions;

public class InvalidWithdrawAmountException extends RuntimeException {
    public InvalidWithdrawAmountException(String message) {
        super(message);
    }
}
