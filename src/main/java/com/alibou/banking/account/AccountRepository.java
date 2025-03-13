package com.alibou.banking.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("UPDATE Account a set a.locked = true WHERE a.id = :accountId")
    @Modifying
    void lockAccount(Long accountId);

    @Query("UPDATE Account a set a.locked = false WHERE a.id = :accountId")
    @Modifying
    void unlockAccount(Long accountId);

    boolean existsByIban(String newIban);
}
