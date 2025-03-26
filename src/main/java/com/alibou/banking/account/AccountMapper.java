package com.alibou.banking.account;

import com.alibou.banking.user.User;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccountMapper {

    public Account mapToAccountEntity(String iban, User savedUser) {
        if (savedUser == null) {
            throw new NullPointerException("User should not be null");
        }
        if (iban == null || iban.isEmpty() || iban.trim().isEmpty()) {
            throw new NullPointerException("Iban should not be empty or null");
        }
        return Account.builder()
                .iban(iban)
                .user(savedUser)
                .locked(true)
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
