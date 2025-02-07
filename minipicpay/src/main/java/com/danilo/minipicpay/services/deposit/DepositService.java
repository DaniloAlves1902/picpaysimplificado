package com.danilo.minipicpay.services.deposit;

import com.danilo.minipicpay.dtos.DepositDTO;
import com.danilo.minipicpay.entities.deposit.Deposit;
import com.danilo.minipicpay.entities.enums.DepositStatus;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.repositories.DepositRepository;
import com.danilo.minipicpay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private UserService userService;

    public void makeDeposit (DepositDTO deposit){
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

    public List<Deposit> findAll (){
        return depositRepository.findAll();
    }

    public Deposit findById (Long id){
        return depositRepository.findById(id).orElseThrow(() -> new RuntimeException("Deposit not found"));
    }
}
