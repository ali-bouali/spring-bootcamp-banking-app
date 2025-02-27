package com.alibou.banking.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query("update  Account a set a.locked=true where a.id= :accountId")
    @Modifying
    void lockAccount(Long accountId);
    @Query("update Account a set a.locked =false where a.id=:id")
    @Modifying
    void unlockAccount(@Param("id")Long accountId);
}
