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
public class TransactionDepositRequest {

    @NotNull(message = "Amount must not be null")
    @Positive(message = "The deposit amount must be a positive number.")
    private BigDecimal depositAmount;


}
