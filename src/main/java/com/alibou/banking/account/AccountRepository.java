package com.alibou.banking.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {


    /*@Query(select * from )
    Account findByStatus(@Param("idAccount") Long id,@Param("status") boolean status);*/


    Optional<Account> findByIdAndIsActive(Long id, boolean status);

}
