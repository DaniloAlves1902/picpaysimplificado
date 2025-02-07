package com.danilo.minipicpay.dtos;

import com.danilo.minipicpay.entities.enums.DepositForm;
import com.danilo.minipicpay.entities.enums.DepositStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DepositDTO(
        Long userId,

        @NotNull
        BigDecimal value,

        @NotNull
        DepositForm depositForm,

        @NotNull
        LocalDateTime createdAt,

        DepositStatus depositStatus

) {
}
