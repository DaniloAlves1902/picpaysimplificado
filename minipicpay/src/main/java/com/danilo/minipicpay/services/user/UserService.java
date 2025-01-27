package com.danilo.minipicpay.services.user;

import com.danilo.minipicpay.dtos.UserDTO;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.exceptions.InsufficientBalanceException;
import com.danilo.minipicpay.exceptions.UserNotFoundException;
import com.danilo.minipicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validationTransaction(User sender, User reciver, BigDecimal amount) {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for transaction");
        }

        if (reciver == null) {
            throw new UserNotFoundException("User not found");
        }

    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User findByDocument(String document) {
        return userRepository.findByDocument(document).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.firstName());
        user.setLastName(userDTO.lastName());
        user.setDocument(userDTO.document());
        user.setEmail(userDTO.email());
        user.setPhoneNumber(userDTO.phoneNumber());
        user.setGender(userDTO.gender());
        user.setNationality(userDTO.nationality());
        user.setAddress(userDTO.address());
        user.setUserStatus(userDTO.userStatus());
        user.setBalance(userDTO.balance());
        user.setUserType(userDTO.userType());

        return userRepository.save(user);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
