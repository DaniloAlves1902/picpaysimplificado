package com.danilo.minipicpay.services.withdraw;

import com.danilo.minipicpay.dtos.WithdrawDTO;
import com.danilo.minipicpay.entities.enums.WithdrawStatus;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.entities.withdraw.Withdraw;
import com.danilo.minipicpay.exceptions.WithdrawNotFoundException;
import com.danilo.minipicpay.repositories.WithdrawRepository;
import com.danilo.minipicpay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Serviço responsável pela gestão de saques dos usuários na aplicação.
 * Este serviço permite realizar saques, recuperar saques realizados e validar as transações.
 */
@Service
public class WithdrawService {

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private UserService userService;

    /**
     * Realiza um saque para um usuário.
     * A validação do saque é feita para garantir que o valor seja válido e que o usuário tenha saldo suficiente.
     * O status do saque é marcado como "successful" após a transação ser realizada.
     *
     * @param withdraw DTO contendo os dados do saque, como o valor e o formulário de saque.
     * @throws WithdrawNotFoundException Se o saque não for encontrado.
     */
    public void makeWithdraw(WithdrawDTO withdraw) {
        User user = userService.findById(withdraw.userId());
        userService.validationWithdraw(user, withdraw.value());

        Withdraw newWithdraw = new Withdraw();

        newWithdraw.setValue(withdraw.value());
        newWithdraw.setUser(user);
        newWithdraw.setWithdrawForm(withdraw.withdrawForm());
        newWithdraw.setCreatedAt(LocalDateTime.now());
        newWithdraw.setWithdrawStatus(WithdrawStatus.SUCCESSFUL);

        user.setBalance(user.getBalance().subtract(withdraw.value()));

        withdrawRepository.save(newWithdraw);
        userService.saveUser(user);
    }

    /**
     * Recupera todos os saques realizados na aplicação.
     *
     * @return Lista de todos os saques.
     * @responseStatus 200 OK
     */
    public List<Withdraw> findAll() {
        return withdrawRepository.findAll();
    }

    /**
     * Recupera um saque específico pelo seu ID.
     *
     * @param id ID do saque.
     * @return O saque correspondente ao ID fornecido.
     * @throws WithdrawNotFoundException Se o saque com o ID fornecido não for encontrado.
     * @responseStatus 200 OK
     * @responseStatus 404 Not Found Caso o saque não seja encontrado.
     */
    public Withdraw findById(Long id) {
        return withdrawRepository.findById(id).orElseThrow(() -> new WithdrawNotFoundException("Withdraw not found"));
    }
}
