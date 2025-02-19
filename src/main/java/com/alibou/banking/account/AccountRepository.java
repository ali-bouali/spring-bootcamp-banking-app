package com.alibou.banking.account;

import com.alibou.banking.user.user;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface  AccountRepository extends JpaRepository<account,Integer> {
  Optional<account> findAllByIban(String iban);


    @Query("SELECT account.user FROM account   WHERE account.id = :accountId")
    Optional<user> findUserByAccountId(@Param("accountId") Long accountId);


}
