package com.alibou.banking.account;

import com.alibou.banking.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


    Account getAccountByUser(User user);

    Account getAccountByIban(String iban);
}
