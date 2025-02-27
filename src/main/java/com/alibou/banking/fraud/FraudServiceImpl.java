package com.alibou.banking.fraud;

import com.alibou.banking.transaction.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FraudServiceImpl implements FraudService {
    private final FraudRepository fraudRepository;

    @Override
    public void fraudDetection(TransactionRequest transactionRequest, BigDecimal balance) {
        BigDecimal amount = transactionRequest.getAmount();
        BigDecimal fraudPercentage = balance.multiply(BigDecimal.valueOf(0.4));
        BigDecimal maxAmount = BigDecimal.valueOf(5000);
        if (amount.compareTo(maxAmount) < 0) {
            throw new RuntimeException("you exceed the allowed amount ");

        } else if (amount.compareTo(fraudPercentage) > 0) {
            throw new RuntimeException("you exceed 40% of the account balance  ");
        }


    }

    @Override
    public void saveFraud(Long transactionId, FraudStatus status, FraudType type) {
        Fraud fraud = Fraud.builder()
                // how the hell w can bring the transaction id ???!
                .type(type)
                .status(status)

                .build();


    }
}
