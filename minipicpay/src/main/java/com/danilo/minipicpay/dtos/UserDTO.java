package com.danilo.minipicpay.dtos;

import com.danilo.minipicpay.entities.enums.Gender;
import com.danilo.minipicpay.entities.enums.UserStatus;
import com.danilo.minipicpay.entities.enums.UserType;

import com.danilo.minipicpay.entities.user.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * A classe <code>UserDTO</code> é um objeto de transferência de dados (DTO) que representa um usuário no sistema.
 * Contém informações pessoais do usuário, como nome, documento, e-mail, telefone, nacionalidade, endereço, status e tipo de usuário, além do saldo associado.
 * 
 * <p>Este DTO é utilizado para enviar e receber informações relacionadas ao usuário em operações no sistema, como cadastro ou atualização de dados.</p>
 * 
 * @param id O identificador único do usuário.
 * @param firstName O primeiro nome do usuário. Não pode ser em branco.
 * @param lastName O sobrenome do usuário. Não pode ser em branco.
 * @param document O documento do usuário (ex: CPF ou CNPJ). Não pode ser em branco.
 * @param email O e-mail do usuário. Deve ser válido e não pode ser em branco.
 * @param phoneNumber O número de telefone do usuário. Não pode ser em branco.
 * @param gender O gênero do usuário. Pode ser nulo.
 * @param nationality A nacionalidade do usuário. Não pode ser em branco.
 * @param address O endereço do usuário. Pode ser nulo.
 * @param userStatus O status do usuário (ex: ATIVO, INATIVO). Pode ser nulo.
 * @param balance O saldo associado ao usuário. Não pode ser nulo.
 * @param userType O tipo de usuário (ex: ADMIN, COMUM). Pode ser nulo.
 */
public record UserDTO(
        /**
         * Identificador único do usuário.
         */
        Long id,

        /**
         * Primeiro nome do usuário. Este campo não pode ser em branco.
         */
        @NotBlank
        String firstName,

        /**
         * Sobrenome do usuário. Este campo não pode ser em branco.
         */
        @NotBlank
        String lastName,

        /**
         * Documento do usuário (ex: CPF ou CNPJ). Este campo não pode ser em branco.
         */
        @NotBlank
        String document,

        /**
         * E-mail do usuário. Este campo não pode ser em branco e deve ser válido.
         */
        @NotBlank
        @Email
        String email,

        /**
         * Número de telefone do usuário. Este campo não pode ser em branco.
         */
        @NotBlank
        String phoneNumber,

        /**
         * Gênero do usuário. Este campo pode ser nulo.
         */
        Gender gender,

        /**
         * Nacionalidade do usuário. Este campo não pode ser em branco.
         */
        @NotBlank
        String nationality,

        /**
         * Endereço do usuário. Este campo pode ser nulo.
         */
        String address,

        /**
         * Status do usuário (ex: ATIVO, INATIVO). Este campo pode ser nulo.
         */
        UserStatus userStatus,

        /**
         * Saldo do usuário. Este campo não pode ser nulo.
         */
        @NotNull
        BigDecimal balance,

        /**
         * Tipo de usuário (ex: ADMIN, COMUM). Este campo pode ser nulo.
         */
        UserType userType


) {

        public static UserDTO fromEntity(User user) {
                return new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getDocument(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getGender(),
                        user.getNationality(),
                        user.getAddress(),
                        user.getUserStatus(),
                        user.getBalance(),
                        user.getUserType()
                );
        }
}
