package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {

    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    private TransactionStatus status;
    private TransactionType type;
    private String destinationIbn;
    private boolean hasFraud;
    private FraudStatus frauStatus;
    private FraudType fraudType;



}
