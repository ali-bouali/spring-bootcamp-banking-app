package com.alibou.banking.transaction;

import com.alibou.banking.contact.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


    Page<Transaction> findAllByUserId(Long userId, Pageable pageable);

@Query("""
         select sum(t.amount) from Transaction t
         where t.user.id = :userId
""")
    BigDecimal getSumTransaction(Long userId);

    List<Transaction> findByType(String type);
    @Query("""
       select sum(t.amount) from Transaction t
       where t.user.id = :userId
       and t.type = :type
       and t.status = "COMPLETED"
 
""")
    BigDecimal getTransactionsByType(String type ,Long userId);
}
