package com.alibou.banking.transaction;

import java.util.List;

public interface TransactionService {

    void deposit(Long userId,TransactionDepositRequest request);
    void withdraw(Long userId,TransactionWithdrawlRequest request);
    void transfer(Long userId,TransactionTransferRequest request);
    List<TransactionResponse> findAllTransaction(Long userId, int page, int size);
}
