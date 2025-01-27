package com.danilo.minipicpay.services.transaction;

import com.danilo.minipicpay.dtos.TransactionDTO;
import com.danilo.minipicpay.entities.transaction.Transaction;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.exceptions.InvalidSenderException;
import com.danilo.minipicpay.repositories.TransactionRepository;
import com.danilo.minipicpay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public void sendTransaction(TransactionDTO transaction) {
        User sender = userService.findById(transaction.senderId());
        User receiver = userService.findById(transaction.reciverId());

        validateSenderAndReceiver(transaction.senderId(), transaction.reciverId());

        userService.validationTransaction(sender, receiver, transaction.amount());

        sender.setBalance(sender.getBalance().subtract(transaction.amount()));
        receiver.setBalance(receiver.getBalance().add(transaction.amount()));

        userService.saveUser(sender);
        userService.saveUser(receiver);
    }

    private void validateSenderAndReceiver(Long senderId, Long receiverId) {
        if (senderId.equals(receiverId)) {
            throw new InvalidSenderException("Sender and receiver cannot be the same");
        }
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

}