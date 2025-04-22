package com.danilo.minipicpay.services.deposit;

import com.danilo.minipicpay.dtos.DepositDTO;
import com.danilo.minipicpay.entities.deposit.Deposit;
import com.danilo.minipicpay.entities.enums.DepositStatus;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.exceptions.DepositNotFoundException;
import com.danilo.minipicpay.repositories.DepositRepository;
import com.danilo.minipicpay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Serviço responsável pela gestão dos depósitos na aplicação.
 * Este serviço realiza depósitos, consulta depósitos existentes e 
 * gerencia as interações com o repositório e os usuários.
 */
@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private UserService userService;

    /**
     * Realiza um depósito para um usuário, atualizando o saldo do usuário e registrando a transação.
     *
     * @param deposit Dados necessários para realizar o depósito (valor, forma de depósito, etc).
     * @throws IllegalArgumentException Se o valor do depósito for inválido ou se houver problemas com o usuário.
     * @apiNote Este método cria um novo depósito, atualiza o saldo do usuário e persiste as informações no banco de dados.
     * @responseStatus 200 OK Caso o depósito seja realizado com sucesso.
     * @responseStatus 400 Bad Request Caso o valor do depósito não seja válido.
     */
    public void makeDeposit(DepositDTO deposit) {
        User user = userService.findById(deposit.userId());
        userService.validationDeposit(user, deposit.value());

        Deposit newDeposit = new Deposit();

        newDeposit.setValue(deposit.value());
        newDeposit.setUser(user);
        newDeposit.setDepositForm(deposit.depositForm());
        newDeposit.setCreatedAt(LocalDateTime.now());
        newDeposit.setDepositStatus(DepositStatus.SUCCESSFUL);

        user.setBalance(user.getBalance().add(deposit.value()));

        depositRepository.save(newDeposit);
        userService.saveUser(user);
    }

    /**
     * Recupera todos os depósitos registrados na aplicação.
     *
     * @return Lista de todos os depósitos.
     * @apiNote Este método retorna todos os depósitos cadastrados no sistema.
     * @responseStatus 200 OK
     */
    public List<Deposit> findAll() {
        return depositRepository.findAll();
    }

    /**
     * Recupera um depósito específico pelo seu ID.
     *
     * @param id ID do depósito.
     * @return O depósito correspondente ao ID fornecido.
     * @throws DepositNotFoundException Se o depósito com o ID não for encontrado.
     * @apiNote Este método retorna o depósito associado ao ID informado ou lança uma exceção caso não exista.
     * @responseStatus 200 OK Caso o depósito seja encontrado.
     * @responseStatus 404 Not Found Caso o depósito não seja encontrado.
     */
    public Deposit findById(Long id) {
        return depositRepository.findById(id).orElseThrow(() -> new DepositNotFoundException("Deposit not found"));
    }
}
