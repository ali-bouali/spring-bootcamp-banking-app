package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionWithFraudResponse {

    private Long transactionId;
    private String userFullName;
    private String transactionDescription;
    private String sourceIban;
    private String destinationIban;
    private BigDecimal amount;
    private TransactionStatus transactionStatus;
    private LocalDateTime transactionDate;
    private LocalDateTime transactionUpdatedAt;
    private FraudStatus fraudStatus;
    private LocalDateTime fraudDate;
    private LocalDateTime fraudUpdatedAt;


}
