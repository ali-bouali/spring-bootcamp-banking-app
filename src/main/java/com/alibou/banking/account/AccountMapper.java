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

    public AccountResponse mapToAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .userFirstName(account.getUser().getFirstName())
                .userLastName(account.getUser().getLastName())
                .userEmail(account.getUser().getEmail())
                .accountIban(account.getIban())
                .locked(account.isLocked())
                .build();
    }
}
