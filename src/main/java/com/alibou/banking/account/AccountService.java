package com.alibou.banking.account;

import com.alibou.banking.transaction.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    final private AccountRepository accountRepository;

    final private TransactionRepository transactionRepository;
    Account getAccountByIban(String iban) {
        Optional<Account>account= accountRepository.findByIban(iban);
        return account.orElse(null);
    }
    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    public double getAmount(String iban) {
        Account account = getAccountByIban(iban);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        double receivedMoney= transactionRepository.sumOfReceiveMoney(account.getIban());
        double sendMoney= transactionRepository.sumOfSentMoney(account.getIban());
        return receivedMoney - sendMoney;
    }
}