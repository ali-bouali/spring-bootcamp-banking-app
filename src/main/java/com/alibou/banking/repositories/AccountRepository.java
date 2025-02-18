package com.alibou.banking.repositories;

import com.alibou.banking.entites.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByIban(String iban);
}
