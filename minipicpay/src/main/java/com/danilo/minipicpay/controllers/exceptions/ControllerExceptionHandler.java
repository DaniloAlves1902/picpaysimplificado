package com.danilo.minipicpay.controllers.exceptions;

import com.danilo.minipicpay.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> threatUserNotFound(UserNotFoundException userNotFoundException) {
        String errorMessage = userNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> threatTransactionNotFound(TransactionNotFoundException transactionNotFoundException) {
        String errorMessage = transactionNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(DepositNotFoundException.class)
    public ResponseEntity<String> threatDepositNotFound(DepositNotFoundException depositNotFoundException) {
        String errorMessage = depositNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(WithdrawNotFoundException.class)
    public ResponseEntity<String> threatWithdrawNotFound(WithdrawNotFoundException withdrawNotFoundException) {
        String errorMessage = withdrawNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(InvalidSenderException.class)
    public ResponseEntity<String> threatInsuficientBalance (InvalidSenderException invalidSenderException){
        String errorMessage = invalidSenderException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> threatInsuficientBalance (InsufficientBalanceException insufficientBalanceException){
        String errorMessage = insufficientBalanceException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(InvalidDepositAmountException.class)
    public ResponseEntity<String> threatInvalidDeposit (InvalidDepositAmountException invalidDepositAmountException){
        String errorMessage = invalidDepositAmountException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(InvalidWithdrawAmountException.class)
    public ResponseEntity<String> threatInvalidWithdraw (InvalidWithdrawAmountException invalidWithdrawAmountException){
        String errorMessage = invalidWithdrawAmountException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
