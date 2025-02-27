package com.alibou.banking.fraud;

import com.alibou.banking.transaction.TransactionRequest;

import java.math.BigDecimal;

public interface FraudService {
    void fraudDetection(TransactionRequest transactionRequest, BigDecimal balance);
    void saveFraud(Long transactionId ,FraudStatus status, FraudType type);

}
