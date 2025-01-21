package com.danilo.minipicpay.dtos;

import java.math.BigDecimal;

public record TransactionDTO(
        BigDecimal amount,
        Long senderId,
        Long reciverId
) {
}
