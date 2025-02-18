package com.alibou.banking.entities.account;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByIbanStartingWith(String firstTwoLetters);

    @Transactional
    @Modifying
    @Query("UPDATE AccountEntity a SET a.iban = :newIban WHERE a.iban = :oldIban")
    int updateIban(String oldIban,String newIban);
}
