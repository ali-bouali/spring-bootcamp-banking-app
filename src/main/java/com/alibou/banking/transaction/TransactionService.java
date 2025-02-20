package com.alibou.banking.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<Transaction> findAllTransactionByAccountId(String accountId, TransactionStatus status){
      var listTransactin=  transactionRepository.findAllTransactionsByStatus(accountId,status);
        return listTransactin;
    }

    public List<Transaction>getAllTransactionDateBefore(String accountId, TransactionStatus status, Date date){
        var listTransaction=transactionRepository.findAllTransactionsByStatusAndDateBefore(accountId,status,date);
        return listTransaction;
    }
}
