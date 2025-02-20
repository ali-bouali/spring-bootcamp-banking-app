package com.alibou.banking.account.service;

import com.alibou.banking.account.Account;
import com.alibou.banking.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

     Account getAccount(Long idAccount);

     void addAccount(Account account);


     void disactivateAccount(Long id);


     void activateAccount(Long id);


     List<Account> getAllAccounts();


    Account getAccountByUser(Long idUser);


     Account getAccountByIban(String iban);


      BigDecimal calculerSoldeCompte(Long accountId) ;

}
