package com.danilo.minipicpay.dtos;

import com.danilo.minipicpay.entities.enums.TransactionStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(
        Long id,

        @NotNull
        Long senderId,

        @NotNull
        Long reciverId,

        @NotNull
        BigDecimal amount,

        @NotNull
        LocalDateTime timestamp,

        TransactionStatus status,

        String description
) {
}
