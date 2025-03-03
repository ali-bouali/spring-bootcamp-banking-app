package com.alibou.banking.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.status='COMPLETED' AND t.user.id=:userId")
    BigDecimal calculateAccountBanlance(Long userId);

    Page<Transaction> findAllByUserId(Long userId, PageRequest pageRequest);
}
