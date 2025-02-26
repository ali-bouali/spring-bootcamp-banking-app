package com.alibou.banking.transaction;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {
    private String raison;
    private BigDecimal amount;
    private LocalDateTime date;
    private TransactionStatus status;
    private TransactionType type;
    private String destinationIban;
    private String sourceIban;
}
