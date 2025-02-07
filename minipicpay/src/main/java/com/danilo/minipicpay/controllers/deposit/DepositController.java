package com.danilo.minipicpay.controllers.deposit;

import com.danilo.minipicpay.dtos.DepositDTO;
import com.danilo.minipicpay.entities.deposit.Deposit;
import com.danilo.minipicpay.services.deposit.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deposit")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping
    public List<Deposit> listAll (){
        return depositService.findAll();
    }

    @GetMapping("{id}")
    public Deposit listById (@PathVariable Long id){
        return depositService.findById(id);
    }

    @PostMapping
    public void makeDeposit (@RequestBody DepositDTO deposit){
        depositService.makeDeposit(deposit);
    }

}
