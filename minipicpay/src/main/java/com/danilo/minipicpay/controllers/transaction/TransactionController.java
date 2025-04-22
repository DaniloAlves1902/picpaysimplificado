package com.danilo.minipicpay.controllers.transaction;

import com.danilo.minipicpay.dtos.TransactionDTO;
import com.danilo.minipicpay.entities.transaction.Transaction;
import com.danilo.minipicpay.services.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão das transações na API.
 * Este controlador expõe endpoints para a criação de transações e listagem de transações existentes.
 */
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Endpoint para listar todas as transações.
     * 
     * @return Lista de transações.
     * @apiNote Retorna todas as transações realizadas no sistema.
     * @responseStatus 200 OK
     */
    @GetMapping
    public List<Transaction> listAll() {
        return transactionService.findAll();
    }

    /**
     * Endpoint para criar uma nova transação.
     * 
     * @param transactionDTO Dados da transação a ser criada, incluindo informações sobre o remetente, destinatário, valor, etc.
     * @apiNote Cria uma nova transação com base nos dados fornecidos. O método chama o serviço para processar a transação.
     * @requestBody TransactionDTO Os dados da transação a serem processados.
     * @responseStatus 201 Created
     */
    @PostMapping
    public void transaction(@RequestBody TransactionDTO transactionDTO) {
        transactionService.makeTransaction(transactionDTO);
    }
}
