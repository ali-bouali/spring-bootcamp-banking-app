package com.alibou.banking.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionWithdrawlRequest {

    private BigDecimal withdrawalAmount;


}
