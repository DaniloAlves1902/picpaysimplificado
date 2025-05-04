package com.danilo.minipicpay.controllers.exceptions;

import com.danilo.minipicpay.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe de tratamento global de exceções para a API.
 * <p>
 * Esta classe captura e lida com exceções específicas lançadas na aplicação e fornece
 * respostas apropriadas, com base no tipo da exceção.
 *
 * @apiNote Cada método é responsável por lidar com uma exceção específica e retornar
 * uma resposta com status e mensagem apropriados.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Captura exceção quando um usuário não é encontrado.
     *
     * @param userNotFoundException Exceção lançada quando um usuário não é encontrado.
     * @return Resposta com status 404 (NOT FOUND) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o usuário não é encontrado.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException userNotFoundException) {
        String errorMessage = userNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    /**
     * Captura exceção quando uma transação não é encontrada.
     *
     * @param transactionNotFoundException Exceção lançada quando uma transação não é encontrada.
     * @return Resposta com status 404 (NOT FOUND) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando a transação não é encontrada.
     */
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handleTransactionNotFound(TransactionNotFoundException transactionNotFoundException) {
        String errorMessage = transactionNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    /**
     * Captura exceção quando um depósito não é encontrado.
     *
     * @param depositNotFoundException Exceção lançada quando um depósito não é encontrado.
     * @return Resposta com status 404 (NOT FOUND) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o depósito não é encontrado.
     */
    @ExceptionHandler(DepositNotFoundException.class)
    public ResponseEntity<String> handleDepositNotFound(DepositNotFoundException depositNotFoundException) {
        String errorMessage = depositNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    /**
     * Captura exceção quando um saque não é encontrado.
     *
     * @param withdrawNotFoundException Exceção lançada quando um saque não é encontrado.
     * @return Resposta com status 404 (NOT FOUND) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o saque não é encontrado.
     */
    @ExceptionHandler(WithdrawNotFoundException.class)
    public ResponseEntity<String> handleWithdrawNotFound(WithdrawNotFoundException withdrawNotFoundException) {
        String errorMessage = withdrawNotFoundException.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    /**
     * Captura exceção quando o remetente de uma transação é inválido.
     *
     * @param invalidSenderException Exceção lançada quando o remetente de uma transação é inválido.
     * @return Resposta com status 400 (BAD REQUEST) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o remetente da transação não é válido.
     */
    @ExceptionHandler(InvalidSenderException.class)
    public ResponseEntity<String> handleInvalidSender(InvalidSenderException invalidSenderException) {
        String errorMessage = invalidSenderException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    /**
     * Captura exceção quando o saldo de um usuário é insuficiente para realizar uma transação.
     *
     * @param insufficientBalanceException Exceção lançada quando o saldo é insuficiente.
     * @return Resposta com status 400 (BAD REQUEST) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o saldo do usuário é insuficiente.
     */
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> handleInsufficientBalance(InsufficientBalanceException insufficientBalanceException) {
        String errorMessage = insufficientBalanceException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    /**
     * Captura exceção quando o valor do depósito é inválido.
     *
     * @param invalidDepositAmountException Exceção lançada quando o valor do depósito é inválido.
     * @return Resposta com status 400 (BAD REQUEST) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o valor do depósito não é válido.
     */
    @ExceptionHandler(InvalidDepositAmountException.class)
    public ResponseEntity<String> handleInvalidDeposit(InvalidDepositAmountException invalidDepositAmountException) {
        String errorMessage = invalidDepositAmountException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    /**
     * Captura exceção quando o valor do saque é inválido.
     *
     * @param invalidWithdrawAmountException Exceção lançada quando o valor do saque é inválido.
     * @return Resposta com status 400 (BAD REQUEST) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o valor do saque não é válido.
     */
    @ExceptionHandler(InvalidWithdrawAmountException.class)
    public ResponseEntity<String> handleInvalidWithdraw(InvalidWithdrawAmountException invalidWithdrawAmountException) {
        String errorMessage = invalidWithdrawAmountException.getMessage();
        return ResponseEntity.badRequest().body(errorMessage);
    }

    /**
     * Captura exceção quando o e-mail já existe no sistema.
     *
     * @param e Exceção lançada quando o e-mail já está em uso.
     * @return Resposta com status 400 (BAD REQUEST) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o e-mail já existe.
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Captura exceção quando o número de telefone já existe no sistema.
     *
     * @param e Exceção lançada quando o número de telefone já está em uso.
     * @return Resposta com status 400 (BAD REQUEST) e a mensagem de erro.
     * @apiNote Retorna uma mensagem de erro quando o número de telefone já existe.
     */
    @ExceptionHandler(PhoneAlreadyExistsException.class)
    public ResponseEntity<String> handlePhoneAlreadyExists(PhoneAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
