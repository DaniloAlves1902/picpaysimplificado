package com.danilo.minipicpay.dtos;

import com.danilo.minipicpay.entities.enums.WithdrawForm;
import com.danilo.minipicpay.entities.enums.WithdrawStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A classe <code>WithdrawDTO</code> é um objeto de transferência de dados (DTO) que representa um saque realizado por um usuário.
 * Contém informações sobre o valor do saque, a forma de saque, o status do saque, e o momento em que o saque foi criado.
 * 
 * <p>Este DTO é utilizado para enviar e receber informações sobre saques em sistemas que requerem integração com a entidade de saques.</p>
 * 
 * @param userId O identificador único do usuário que realizou o saque.
 * @param value O valor do saque. Não pode ser nulo.
 * @param withdrawForm A forma de saque utilizada. Não pode ser nula.
 * @param createdAt A data e hora em que o saque foi realizado. Não pode ser nula.
 * @param withdrawStatus O status do saque, que pode indicar seu estado atual (ex: PENDENTE, CONCLUÍDO). Não pode ser nulo.
 */
public record WithdrawDTO(
        /**
         * Identificador único do usuário que realizou o saque.
         */
        Long userId,

        /**
         * Valor do saque. Este campo não pode ser nulo.
         */
        @NotNull
        BigDecimal value,

        /**
         * Forma de saque utilizada. Este campo não pode ser nulo.
         */
        @NotNull
        WithdrawForm withdrawForm,

        /**
         * Data e hora em que o saque foi realizado. Este campo não pode ser nulo.
         */
        @NotNull
        LocalDateTime createdAt,

        /**
         * Status do saque. Este campo não pode ser nulo.
         */
        @NotNull
        WithdrawStatus withdrawStatus

) {
}
