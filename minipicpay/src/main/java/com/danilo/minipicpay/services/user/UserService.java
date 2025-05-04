package com.danilo.minipicpay.services.user;

import com.danilo.minipicpay.dtos.UserDTO;
import com.danilo.minipicpay.entities.enums.UserStatus;
import com.danilo.minipicpay.entities.user.User;
import com.danilo.minipicpay.exceptions.*;
import com.danilo.minipicpay.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela gestão de usuários na aplicação.
 * Este serviço lida com as operações de validação, criação, atualização e remoção de usuários, além de validações de transações.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Valida uma transação entre um remetente e um destinatário.
     * Verifica se o remetente possui saldo suficiente e se o destinatário é válido.
     *
     * @param sender  O usuário remetente da transação.
     * @param reciver O usuário destinatário da transação.
     * @param amount  O valor da transação.
     * @throws InsufficientBalanceException Se o remetente não tiver saldo suficiente para realizar a transação.
     * @throws UserNotFoundException        Se o destinatário não for encontrado.
     */
    public void validationTransaction(User sender, User reciver, BigDecimal amount) {
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for transaction");
        }

        if (reciver == null) {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Valida um depósito feito por um usuário.
     * Verifica se o valor do depósito é válido e se o usuário existe.
     *
     * @param user  O usuário que está realizando o depósito.
     * @param value O valor do depósito.
     * @throws InvalidDepositAmountException Se o valor do depósito for inválido (menor ou igual a zero).
     * @throws UserNotFoundException         Se o usuário não for encontrado.
     */
    public void validationDeposit(User user, BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidDepositAmountException("Invalid deposit amount");
        }

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Valida um saque realizado por um usuário.
     * Verifica se o valor do saque é válido e se o usuário existe.
     *
     * @param user  O usuário que está realizando o saque.
     * @param value O valor do saque.
     * @throws InvalidWithdrawAmountException Se o valor do saque for inválido (menor ou igual a zero).
     * @throws UserNotFoundException          Se o usuário não for encontrado.
     */
    public void validationWithdraw(User user, BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidWithdrawAmountException("Invalid withdraw amount");
        }

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
    }

    /**
     * Recupera todos os usuários cadastrados no sistema.
     *
     * @return Lista de todos os usuários.
     * @responseStatus 200 OK
     */
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }


    /**
     * Recupera um usuário pelo seu ID.
     *
     * @param id ID do usuário.
     * @return O usuário correspondente ao ID fornecido.
     * @throws UserNotFoundException Se o usuário com o ID não for encontrado.
     * @responseStatus 200 OK
     * @responseStatus 404 Not Found Caso o usuário não seja encontrado.
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    /**
     * Recupera um usuário pelo seu documento (ex: CPF ou CNPJ).
     *
     * @param document Documento do usuário.
     * @return O usuário correspondente ao documento fornecido.
     * @throws UserNotFoundException Se o usuário com o documento não for encontrado.
     * @responseStatus 200 OK
     * @responseStatus 404 Not Found Caso o usuário não seja encontrado.
     */
    public User findByDocument(String document) {
        return userRepository.findByDocument(document).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    /**
     * Cria um novo usuário com base nos dados fornecidos.
     *
     * @param user Dados do usuário para criação.
     * @return O usuário recém-criado.
     * @throws IllegalArgumentException Se os dados fornecidos forem inválidos.
     * @responseStatus 201 Created Caso o usuário seja criado com sucesso.
     */
    public User createUser(User user) {
        if (user.getBalance() == null) {
            user.setBalance(BigDecimal.ZERO);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("This email already exists");
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new PhoneAlreadyExistsException("This phone already exists");
        }

        return userRepository.save(user);
    }

    /**
     * Salva um usuário no banco de dados.
     *
     * @param user O usuário a ser salvo.
     * @return O usuário salvo.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deleta um usuário do sistema com base no seu ID.
     *
     * @param id ID do usuário a ser deletado.
     * @throws UserNotFoundException Se o usuário com o ID não for encontrado.
     * @responseStatus 204 No Content Caso o usuário seja deletado com sucesso.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
