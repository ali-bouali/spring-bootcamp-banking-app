package com.alibou.banking.transaction;


import com.alibou.banking.user.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<transaction, Long> {
    List<transaction> findByAmountGreaterThan(BigDecimal amount);
    List<transaction>findByDateBefore(Date date);
    List<transaction> findByUser(user user);

}
