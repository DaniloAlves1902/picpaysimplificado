package com.danilo.minipicpay.dtos;

import com.danilo.minipicpay.entities.enums.TransactionStatus;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A classe <code>TransactionDTO</code> é um objeto de transferência de dados (DTO) que representa uma transação realizada entre dois usuários.
 * Contém informações como o valor da transação, os usuários envolvidos, o status da transação e a data/hora em que ocorreu.
 * 
 * <p>Este DTO é utilizado para enviar e receber informações sobre transações no sistema, como em transferências de valores entre usuários.</p>
 * 
 * @param id O identificador único da transação.
 * @param senderId O identificador do usuário remetente da transação. Não pode ser nulo.
 * @param reciverId O identificador do usuário destinatário da transação. Não pode ser nulo.
 * @param amount O valor da transação. Não pode ser nulo.
 * @param timestamp A data e hora em que a transação foi realizada. Não pode ser nulo.
 * @param status O status da transação (por exemplo, PENDENTE, CONCLUÍDA, etc.). Pode ser nulo.
 * @param description A descrição opcional da transação. Pode ser nulo.
 */
public record TransactionDTO(
        /**
         * Identificador único da transação.
         */
        Long id,

        /**
         * Identificador do usuário remetente. Este campo não pode ser nulo.
         */
        @NotNull
        Long senderId,

        /**
         * Identificador do usuário destinatário. Este campo não pode ser nulo.
         */
        @NotNull
        Long reciverId,

        /**
         * Valor da transação. Este campo não pode ser nulo.
         */
        @NotNull
        BigDecimal amount,

        /**
         * Data e hora em que a transação foi realizada. Este campo não pode ser nulo.
         */
        @NotNull
        LocalDateTime timestamp,

        /**
         * Status da transação, indicando seu estado atual (exemplo: PENDENTE, CONCLUÍDA).
         * Este campo pode ser nulo.
         */
        TransactionStatus status,

        /**
         * Descrição opcional da transação. Pode ser nulo.
         */
        String description
) {
}
