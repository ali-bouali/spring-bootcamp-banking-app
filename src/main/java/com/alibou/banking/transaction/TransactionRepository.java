package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

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

    @Query("""
            SELECT
                t.id as transactionId,
                concat(t.user.firstName, ' ', t.user.lastName) as userFullName,
                t.description as transactionDescription,
                t.sourceIban as sourceIban,
                t.destinationIban as destinationIban,
                t.amount as amount,
                t.status as transactionStatus,
                t.createdDate as transactionDate,
                t.lastModifiedDate as transactionUpdatedAt,
                t.fraud.status as fraudStatus,
                t.fraud.date as fraudDate,
                t.fraud.lastModifiedDate as fraudUpdatedAt
                FROM Transaction t
            INNER JOIN Fraud f ON f.transaction.id = t.id
            WHERE f.type = :type
            """)
    Page<TransactionWithFraudProjection> findAllTransactionsHavingFraudProj(FraudType type, Pageable pageable);
}
