package com.danilo.minipicpay.controllers.user;

import com.danilo.minipicpay.dtos.UserDTO;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> listAll (){
        return userService.findAll();
    }

    @GetMapping("/document/{document}")
    public User listByDocument (@PathVariable String document){
        return userService.findByDocument(document);
    }

    @GetMapping("{id}")
    public User listById (@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO dataUser){
        return userService.createUser(dataUser);
    }

    @DeleteMapping
    public void deleteUser (@PathVariable Long id){
        userService.deleteUser(id);
    }


}
