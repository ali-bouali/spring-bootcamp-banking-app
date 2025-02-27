package com.alibou.banking.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("""
                    SELECT SUM(t.amount) AS balance
                    FROM Transaction t
                    WHERE t.status = 'COMPLETED'
                    AND t.user.id = :userId
            """)
    BigDecimal calculateAccountBalance(Long userId);

    Page<Transaction> findAllByUserId(Long userId, Pageable pageable);
}
