package com.alibou.banking.transaction;

import com.alibou.banking.contact.ContactRequest;
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
public class TransactionTransferRequest {

    private String raison;
    private BigDecimal transferAmount;
    private String destinationIban;
    private String sourceIban;

    private ContactRequest contact;
}
