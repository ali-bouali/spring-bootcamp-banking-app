package com.alibou.banking.transaction.service.impl;

import com.alibou.banking.account.Account;
import com.alibou.banking.account.service.AccountService;
import com.alibou.banking.fraud.FraudRepository;
import com.alibou.banking.transaction.Transaction;
import com.alibou.banking.transaction.TransactionRepository;
import com.alibou.banking.transaction.service.TransactionService;


import java.math.BigDecimal;
import java.util.List;

public class TransactionServiceImpl  implements TransactionService {

 // private final TransactionRepository transactionRepository;

  private final AccountService accountService;


 // private final FraudRepository fraudRepository;


  public TransactionServiceImpl(TransactionRepository transactionRepository, AccountService accountService , FraudRepository fraudRepository  ) {
   // this.transactionRepository = transactionRepository;
    this.accountService = accountService;
    //this.fraudRepository = fraudRepository ;
  }


  @Override
  public void processVirement(String sourceIban, String destinationIban, BigDecimal amount) {
    Account senderAccount = accountService.getAccountByIban(sourceIban);
    Account receiverAccount = accountService.getAccountByIban(sourceIban);
    if (senderAccount == null) {
      throw new IllegalArgumentException("Sender account not found.");
    }

    if (receiverAccount == null) {
      throw new IllegalArgumentException("Recipient account not found.");
    }
  }

  @Override
  public void debitAccount(String sourceIban, String destinationIban, BigDecimal amount) {

  }

  @Override
  public List<Transaction> getTransactionsByAccountId(int accountId) {
    return List.of();
  }
}