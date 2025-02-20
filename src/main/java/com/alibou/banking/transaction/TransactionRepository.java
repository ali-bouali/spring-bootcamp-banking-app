package com.alibou.banking.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    @Query("""
    SELECT SUM(t.amount) FROM Transaction t WHERE t.sourceIban = :sourceIban and t.status = :COMPLETED
""")
    double sumOfReceiveMoney(@Param("destinationIban") String sourceIban);


    @Query("""
    SELECT SUM(t.amount) FROM Transaction t WHERE t.destinationIban = :destinationIban and t.status = :COMPLETED
""")
    double sumOfSentMoney(@Param("destinationIban") String destinationIban);

    @Query("""

    SELECT t from Transaction t where t.status = :status and t.destinationIban = :destinationIban or t.sourceIban = :destinationIban 
""")
    List<Transaction>findAllTransactionsByStatus( String accountId, @Param("status")TransactionStatus status);

    @Query("""

    SELECT t from Transaction t where t.status = :status and t.destinationIban = :destinationIban or t.sourceIban = :destinationIban  AND t.date <= :endDate
                                                                                                                                      
""")
    List<Transaction>findAllTransactionsByStatusAndDateBefore( String accountId, @Param("status")TransactionStatus status,@Param("endDate") Date endDate);

}
