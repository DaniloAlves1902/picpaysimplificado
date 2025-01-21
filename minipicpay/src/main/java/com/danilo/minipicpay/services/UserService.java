package com.danilo.minipicpay.services;

import com.danilo.minipicpay.entities.User;
import com.danilo.minipicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validationTransaction(User sender, BigDecimal amount){
        if (sender.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("insufficient balance");
        }
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
