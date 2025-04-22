package com.danilo.minipicpay.dtos;

import com.danilo.minipicpay.entities.enums.DepositForm;
import com.danilo.minipicpay.entities.enums.DepositStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A classe <code>DepositDTO</code> é um objeto de transferência de dados (DTO) que representa um depósito realizado por um usuário.
 * Contém informações como o valor do depósito, a forma de depósito, o status do depósito, e o momento em que o depósito foi criado.
 * 
 * <p>Este DTO é utilizado para enviar e receber informações sobre depósitos em sistemas que requerem integração com a entidade de depósitos.</p>
 * 
 * @param userId O identificador único do usuário que realizou o depósito.
 * @param value O valor do depósito. Não pode ser nulo.
 * @param depositForm A forma de depósito utilizada. Não pode ser nula.
 * @param createdAt A data e hora em que o depósito foi criado. Não pode ser nula.
 * @param depositStatus O status do depósito, que pode ser nulo (por exemplo, para indicar que o depósito ainda não foi processado).
 */
public record DepositDTO(
        /**
         * Identificador único do usuário.
         */
        Long userId,

        /**
         * Valor do depósito. Este campo não pode ser nulo.
         */
        @NotNull
        BigDecimal value,

        /**
         * Forma de depósito utilizada. Este campo não pode ser nulo.
         */
        @NotNull
        DepositForm depositForm,

        /**
         * Data e hora de criação do depósito. Este campo não pode ser nulo.
         */
        @NotNull
        LocalDateTime createdAt,

        /**
         * Status do depósito. Este campo pode ser nulo.
         */
        DepositStatus depositStatus

) {
}
