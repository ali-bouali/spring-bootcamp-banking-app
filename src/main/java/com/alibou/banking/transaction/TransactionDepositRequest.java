package com.alibou.banking.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDepositRequest {
    @NotNull(message = "the amount must be not null")
    @Positive(message = "the amount should be positive")
    private BigDecimal depositAmount;


}
