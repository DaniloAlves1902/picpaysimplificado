package com.danilo.minipicpay.entities.deposit;

import com.danilo.minipicpay.dtos.DepositDTO;
import com.danilo.minipicpay.entities.enums.DepositForm;
import com.danilo.minipicpay.entities.enums.DepositStatus;
import com.danilo.minipicpay.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private BigDecimal value;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DepositForm depositForm;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DepositStatus depositStatus;


    public Deposit() {
        this.createdAt = LocalDateTime.now();
    }

    public Deposit(User user, BigDecimal value, DepositForm depositForm, LocalDateTime createdAt, DepositStatus depositStatus) {
        this.user = user;
        this.value = value;
        this.depositForm = depositForm;
        this.createdAt = createdAt;
        this.depositStatus = depositStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull User getUser() {
        return user;
    }

    public void setUser(@NotNull User user) {
        this.user = user;
    }

    public @NotNull BigDecimal getValue() {
        return value;
    }

    public void setValue(@NotNull BigDecimal value) {
        this.value = value;
    }

    public @NotNull DepositForm getDepositForm() {
        return depositForm;
    }

    public void setDepositForm(@NotNull DepositForm depositForm) {
        this.depositForm = depositForm;
    }

    public @NotNull LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NotNull LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public @NotNull DepositStatus getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(@NotNull DepositStatus depositStatus) {
        this.depositStatus = depositStatus;
    }
}
