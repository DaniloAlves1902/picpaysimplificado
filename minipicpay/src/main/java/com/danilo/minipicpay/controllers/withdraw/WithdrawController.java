package com.danilo.minipicpay.controllers.withdraw;

import com.danilo.minipicpay.dtos.WithdrawDTO;
import com.danilo.minipicpay.entities.withdraw.Withdraw;
import com.danilo.minipicpay.services.withdraw.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão das retiradas de valores na API.
 * Este controlador expõe endpoints para a realização de retiradas, 
 * bem como a consulta de retiradas existentes.
 */
@RestController
@RequestMapping("/api/withdraw")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    /**
     * Endpoint para listar todas as retiradas.
     * 
     * @return Lista de retiradas realizadas.
     * @apiNote Retorna todas as retiradas processadas no sistema.
     * @responseStatus 200 OK
     */
    @GetMapping
    public List<Withdraw> listAll() {
        return withdrawService.findAll();
    }

    /**
     * Endpoint para buscar uma retirada específica pelo seu ID.
     * 
     * @param id ID da retirada a ser buscada.
     * @return A retirada correspondente ao ID fornecido.
     * @apiNote Busca uma retirada específica pelo seu ID.
     * @responseStatus 200 OK
     * @responseStatus 404 Not Found Caso a retirada não seja encontrada.
     */
    @GetMapping("/{id}")
    public Withdraw listById(@PathVariable Long id) {
        return withdrawService.findById(id);
    }

    /**
     * Endpoint para realizar uma retirada.
     * 
     * @param withdraw Dados da retirada a ser realizada.
     * @apiNote Realiza uma retirada de valor no sistema com base nos dados fornecidos.
     * @requestBody WithdrawDTO Dados necessários para realizar a retirada.
     * @responseStatus 200 OK Caso a retirada seja bem-sucedida.
     * @responseStatus 400 Bad Request Caso ocorra algum erro, como saldo insuficiente.
     */
    @PostMapping
    public void makeWithdraw(@RequestBody WithdrawDTO withdraw) {
        withdrawService.makeWithdraw(withdraw);
    }
}
