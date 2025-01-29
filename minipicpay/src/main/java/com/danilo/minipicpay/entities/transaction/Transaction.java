package com.danilo.minipicpay.entities.transaction;

import com.danilo.minipicpay.entities.enums.TransactionStatus;
import com.danilo.minipicpay.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@Table(name = "transactions")

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "senderId", nullable = false)
    private User sender;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "recive_id", nullable = false)
    private User reciver;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private String description;

    public Transaction(Long id, User sender, User recive, BigDecimal amount, LocalDateTime timestamp, TransactionStatus status, String description) {
        this.id = id;
        this.sender = sender;
        this.reciver = recive;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
        this.description = description;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull User getSender() {
        return sender;
    }

    public void setSender(@NotNull User sender) {
        this.sender = sender;
    }

    public @NotNull User getReciver() {
        return reciver;
    }

    public void setReciver(@NotNull User reciver) {
        this.reciver = reciver;
    }

    public @NotNull BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull BigDecimal amount) {
        this.amount = amount;
    }

    public @NotNull LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(@NotNull LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
