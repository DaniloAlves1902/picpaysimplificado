package com.danilo.minipicpay.repositories;

import com.danilo.minipicpay.entities.withdraw.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {
}
