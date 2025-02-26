package com.alibou.banking.transaction;

import java.util.List;

public interface TransactionService {

    void withdraw(CreateTransactionRequest transactionRequest);
    void deposit(CreateTransactionRequest transactionRequest);
    void transfer(CreateTransactionRequest transactionRequest,Long userId);
    void validateTransaction(TransactionRequest transactionRequest);
    List<TransactionResponse> findAllTransaction(Long userId, int page, int size);
    TransactionResponse findById(Long transactionId);

}
