package com.alibou.banking.transaction;

import com.alibou.banking.user.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface transactionRepository extends JpaRepository<transaction, Long> {
    List<transaction> findByAmountGreaterThan(BigDecimal amount);

    List<transaction> findByDateAndUser(LocalDateTime date, user user);

}
