package com.alibou.banking.transaction.service;

import com.alibou.banking.transaction.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    void processVirement(String sourceIban, String destinationIban, BigDecimal amount);
    void debitAccount (String sourceIban, String destinationIban, BigDecimal amount);
    List<Transaction> getTransactionsByAccountId(int accountId);
}
