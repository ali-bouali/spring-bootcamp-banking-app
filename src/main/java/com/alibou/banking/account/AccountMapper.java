package com.alibou.banking.account;

import com.alibou.banking.user.User;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public Account mapToAccountEntity(String iban, User savedUser) {
        return Account.builder()
                .iban(iban)
                .user(savedUser)
                .build();
    }
}
