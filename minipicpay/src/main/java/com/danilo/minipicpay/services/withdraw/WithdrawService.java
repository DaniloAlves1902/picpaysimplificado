package com.danilo.minipicpay.services.withdraw;

import com.danilo.minipicpay.dtos.WithdrawDTO;
import com.danilo.minipicpay.entities.enums.UserType;
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

@Service
public class WithdrawService {

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private UserService userService;

    public void makeWithdraw (WithdrawDTO withdraw){
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

    public List<Withdraw> findAll (){
        return withdrawRepository.findAll();
    }

    public Withdraw findById (Long id){
        return withdrawRepository.findById(id).orElseThrow(() -> new WithdrawNotFoundException("Withdraw not foun"));
    }
}
