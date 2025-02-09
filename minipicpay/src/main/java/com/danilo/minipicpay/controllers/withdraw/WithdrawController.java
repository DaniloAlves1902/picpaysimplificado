package com.danilo.minipicpay.controllers.withdraw;

import com.danilo.minipicpay.dtos.WithdrawDTO;
import com.danilo.minipicpay.entities.withdraw.Withdraw;
import com.danilo.minipicpay.services.withdraw.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/withdraw")
public class WithdrawController {

    @Autowired
    private WithdrawService withdrawService;

    @GetMapping
    public List<Withdraw> listAll (){
        return withdrawService.findAll();
    }

    @GetMapping("/{id}")
    public Withdraw listById (@PathVariable Long id){
        return withdrawService.findById(id);
    }

    @PostMapping
    public void makeWithdraw (@RequestBody WithdrawDTO withdraw){
        withdrawService.makeWithdraw(withdraw);
    }
}
