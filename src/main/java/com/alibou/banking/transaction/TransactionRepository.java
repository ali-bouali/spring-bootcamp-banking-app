package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudType;
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

    @Query("""
            SELECT t FROM Transaction t
            INNER JOIN Fraud f ON f.transaction.id = t.id
            WHERE f.type = :type
            """)
    Page<Transaction> findAllTransactionsHavingFraud(FraudType type, Pageable pageable);
}
