package com.alibou.banking.account;

import com.alibou.banking.user.User;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public Account mapToAccount(User user, String iban) {
        return Account.builder().iban(iban).user(user).locked(true).build();
    }
}
