package com.danilo.minipicpay.repositories;

import com.danilo.minipicpay.entities.deposit.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
