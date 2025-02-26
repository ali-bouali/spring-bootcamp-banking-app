package com.alibou.banking.transaction;


import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {
    public Transaction toTransactionEntity(TransactionRequest transactionRequest) {
        return Transaction.builder()
                .destinationIban(transactionRequest.getDestinationIban())
                .sourceIban(transactionRequest.getSourceIban())
                .amount(transactionRequest.getAmount())
                .date(transactionRequest.getDate())
                .status(transactionRequest.getStatus())
                .type(transactionRequest.getType())
                .description(transactionRequest.getDescription())
                .build();

    }
    public TransactionResponse toTansactionResponse(Transaction transaction) {
        return null;
    }
}
