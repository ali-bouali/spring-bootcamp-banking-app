package com.alibou.banking.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionWithdrawalRequest {
    @NotNull(message = "the amount must be not null")
    @Positive(message = "the amount should be positive")

    private BigDecimal withdrawalAmount;
}
