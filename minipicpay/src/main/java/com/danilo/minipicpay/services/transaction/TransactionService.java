package com.danilo.minipicpay.services.transaction;

import com.danilo.minipicpay.dtos.TransactionDTO;
import com.danilo.minipicpay.entities.enums.TransactionStatus;
import com.danilo.minipicpay.entities.transaction.Transaction;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.exceptions.InvalidSenderException;
import com.danilo.minipicpay.repositories.TransactionRepository;
import com.danilo.minipicpay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public void makeTransaction(TransactionDTO transaction) {

        User sender = userService.findById(transaction.senderId());
        User reciver = userService.findById(transaction.reciverId());

        userService.validationTransaction(sender, reciver, transaction.amount());

        Transaction newTransaction = new Transaction();

        validateSenderAndReceiver(transaction.senderId(), transaction.reciverId());

        newTransaction.setAmount(transaction.amount());
        newTransaction.setSender(sender);
        newTransaction.setReciver(reciver);
        newTransaction.setTimestamp(LocalDateTime.now());
        newTransaction.setDescription(transaction.description());
        newTransaction.setStatus(TransactionStatus.SUCCESSFUL);

        sender.setBalance(sender.getBalance().subtract(transaction.amount()));
        reciver.setBalance(reciver.getBalance().add(transaction.amount()));

        transactionRepository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(reciver);
    }


    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    private void validateSenderAndReceiver(Long senderId, Long receiverId) {
        if (senderId.equals(receiverId)) {
            throw new InvalidSenderException("Sender and receiver cannot be the same");
        }
    }

}