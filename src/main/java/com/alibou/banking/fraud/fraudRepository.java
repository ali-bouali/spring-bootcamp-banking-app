package com.alibou.banking.fraud;

import com.alibou.banking.transaction.transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface fraudRepository extends JpaRepository<fraud, Long> {

    List<fraud> findByType(fraudType type);

    Optional<fraud> findByTransaction(transaction transaction);

    long countByType(fraudType type);


}
