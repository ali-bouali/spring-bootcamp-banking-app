package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;

import java.util.List;

public interface TransactionService {

    void deposit(Long userId, TransactionDepositRequest request);
    void withdraw(Long userId, TransactionWithdrawalRequest request);
    void transfer(Long userId, TransactionTransferRequest request);
    List<TransactionResponse> finaAllTransactions(Long userId, int page, int size);
    List<TransactionWithFraudResponse> findAllTransactionsWithFraud(int page, int size, FraudType type);
    void changeTransactionFraudStatus(Long transactionId, FraudStatus fraudStatus);
}
