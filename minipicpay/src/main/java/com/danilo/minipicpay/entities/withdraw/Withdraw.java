package com.danilo.minipicpay.entities.withdraw;

import com.danilo.minipicpay.entities.enums.WithdrawForm;
import com.danilo.minipicpay.entities.enums.WithdrawStatus;
import com.danilo.minipicpay.entities.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdraw")
public class Withdraw {
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
    private WithdrawForm withdrawForm;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private WithdrawStatus withdrawStatus;

    public Withdraw() {
        this.createdAt = LocalDateTime.now();
    }
    public Withdraw(User user, BigDecimal value, WithdrawForm withdrawForm, LocalDateTime createdAt, WithdrawStatus withdrawStatus) {
        this.user = user;
        this.value = value;
        this.withdrawForm = withdrawForm;
        this.createdAt = createdAt;
        this.withdrawStatus = withdrawStatus;
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

    public @NotNull WithdrawForm getWithdrawForm() {
        return withdrawForm;
    }

    public void setWithdrawForm(@NotNull WithdrawForm withdrawForm) {
        this.withdrawForm = withdrawForm;
    }

    public @NotNull LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NotNull LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public @NotNull WithdrawStatus getWithdrawStatus() {
        return withdrawStatus;
    }

    public void setWithdrawStatus(@NotNull WithdrawStatus withdrawStatus) {
        this.withdrawStatus = withdrawStatus;
    }
}