package com.danilo.minipicpay.dtos;

import com.danilo.minipicpay.entities.enums.Gender;
import com.danilo.minipicpay.entities.enums.UserStatus;
import com.danilo.minipicpay.entities.enums.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UserDTO(
        Long id,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        String document,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String password,

        @NotBlank
        String phoneNumber,

        Gender gender,

        @NotBlank
        String nationality,

        String address,

        UserStatus userStatus,

        @NotNull
        BigDecimal balance,

        UserType userType
) {
}
