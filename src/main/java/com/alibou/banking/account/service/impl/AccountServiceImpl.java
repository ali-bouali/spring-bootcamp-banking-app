package com.alibou.banking.account.service.impl;

import com.alibou.banking.account.Account;
import com.alibou.banking.account.AccountRepository;
import com.alibou.banking.account.service.AccountService;
import com.alibou.banking.user.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
public class AccountServiceImpl implements AccountService {


    private  final  AccountRepository accountRepository;

    private  final UserService userService;

    public AccountServiceImpl(AccountRepository accountRepository ,UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }
    @Override
    public Account getAccount(Long idAccount) throws EntityNotFoundException ,IllegalArgumentException  {
        if(idAccount == null) {
           throw new IllegalArgumentException ("Account ID cannot be null");
        }
        return   accountRepository.findById(idAccount).orElseThrow(() -> new EntityNotFoundException("Account not found"));

    }

    @Override
    public void addAccount(Account account) throws IllegalArgumentException  {
        if(account == null) {
            throw new IllegalArgumentException ("Account  cannot be null");
        }
        accountRepository.save(account);
    }

    @Override
    public void disactivateAccount(Long idAccount) {
        changeAccountStatus(idAccount, false);

    }

    @Override
    public void activateAccount(Long idAccount) {
        changeAccountStatus(idAccount, true);
    }



    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountByUser(Long idUser) {
        if (idUser == null) {
            throw new IllegalArgumentException("idUser  cannot be null");
        }
        var user = userService.getUserById(idUser);

        var account = accountRepository.getAccountByUser(user);
        if (account == null) {
            throw new EntityNotFoundException("Account not found");
        }
        return account;

    }

    @Override
     public Account getAccountByIban(String iban)
    {
        return accountRepository.getAccountByIban(iban);
    }


    @Override
    public BigDecimal calculerSoldeCompte(Long accountId)
    {
        return null ;
    }



    private void changeAccountStatus(Long idAccount, boolean isActive) {
        if (idAccount == null) {
            throw new IllegalArgumentException("Account  cannot be null");
        }
        //Optional<Account> account =  accountRepository.findById(idAccount) ;
        accountRepository.findById(idAccount).ifPresentOrElse(account -> {
            account.setActive(isActive);
            accountRepository.save(account);
        }, () -> {
            log.error("Account not found");
            throw new EntityNotFoundException("Account not found");
        });
    }
}
