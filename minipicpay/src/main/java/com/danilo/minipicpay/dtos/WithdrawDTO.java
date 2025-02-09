package com.danilo.minipicpay.dtos;

import com.danilo.minipicpay.entities.enums.WithdrawForm;
import com.danilo.minipicpay.entities.enums.WithdrawStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record WithdrawDTO(
        Long userId,

        @NotNull
        BigDecimal value,

        @NotNull
        WithdrawForm withdrawForm,

        @NotNull
        LocalDateTime createdAt,

        @NotNull
        WithdrawStatus withdrawStatus
        ) {
}
