package com.alibou.banking.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface accountRepository extends JpaRepository<account, Long> {
    account findByIban(String iban);
}
