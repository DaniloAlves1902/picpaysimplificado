package com.danilo.minipicpay.services.user;

import com.danilo.minipicpay.dtos.UserDTO;
import com.danilo.minipicpay.entities.enums.TransactionStatus;
import com.danilo.minipicpay.entities.enums.UserStatus;
import com.danilo.minipicpay.entities.transaction.Transaction;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.exceptions.InsufficientBalanceException;
import com.danilo.minipicpay.exceptions.UserNotFoundException;
import com.danilo.minipicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User findByDocument(String document) {
        return userRepository.findByDocument(document).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User createUser(UserDTO dataUser) {
        User newUser = new User();
        newUser.setFirstName(dataUser.firstName());
        newUser.setLastName(dataUser.lastName());
        newUser.setDocument(dataUser.document());
        newUser.setEmail(dataUser.email());
        newUser.setPassword(dataUser.password());
        newUser.setPhoneNumber(dataUser.phoneNumber());
        newUser.setGender(dataUser.gender());
        newUser.setNationality(dataUser.nationality());
        newUser.setAddress(dataUser.address());
        newUser.setUserStatus(UserStatus.ACTIVE);
        newUser.setBalance(BigDecimal.ZERO);
        newUser.setUserType(dataUser.userType());
        this.saveUser(newUser);
        return newUser;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
