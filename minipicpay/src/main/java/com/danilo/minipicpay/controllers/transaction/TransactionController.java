package com.danilo.minipicpay.controllers.transaction;

import com.danilo.minipicpay.dtos.TransactionDTO;
import com.danilo.minipicpay.entities.transaction.Transaction;
import com.danilo.minipicpay.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> listAll (){
        return transactionService.findAll();
    }

    @PostMapping
    public void transaction (@RequestBody TransactionDTO transactionDTO){
        transactionService.makeTransaction(transactionDTO);
    }
}
