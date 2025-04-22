package com.danilo.minipicpay.controllers.deposit;

import com.danilo.minipicpay.dtos.DepositDTO;
import com.danilo.minipicpay.entities.deposit.Deposit;
import com.danilo.minipicpay.services.deposit.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável por gerenciar os depósitos dos usuários.
 * Este controller oferece endpoints para listar depósitos existentes e realizar um novo depósito.
 * 
 * @apiNote Utilize este controller para consultar e realizar depósitos na conta do usuário.
 */
@RestController
@RequestMapping("/api/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    /**
     * Endpoint para listar todos os depósitos realizados.
     * 
     * @return Lista de depósitos realizados.
     * @apiNote Retorna todos os depósitos registrados no sistema.
     */
    @GetMapping
    public List<Deposit> listAll () {
        return depositService.findAll();
    }

    /**
     * Endpoint para consultar um depósito específico pelo seu ID.
     * 
     * @param id O ID do depósito a ser consultado.
     * @return O depósito correspondente ao ID informado.
     * @throws ResourceNotFoundException Se o depósito com o ID especificado não for encontrado.
     * @apiNote Retorna os detalhes de um depósito específico.
     */
    @GetMapping("{id}")
    public Deposit listById (@PathVariable Long id) {
        return depositService.findById(id);
    }

    /**
     * Endpoint para realizar um novo depósito.
     * 
     * @param deposit Os dados do depósito a ser realizado, incluindo o valor e a forma de depósito.
     * @apiNote Realiza um depósito na conta do usuário utilizando os dados fornecidos no corpo da requisição.
     */
    @PostMapping
    public void makeDeposit (@RequestBody DepositDTO deposit) {
        depositService.makeDeposit(deposit);
    }
}
