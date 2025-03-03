package com.alibou.banking.transaction;


import lombok.*;

import java.math.BigDecimal;
import com.alibou.banking.contact.ContactRequest;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionTransferRequest {

    private String reason;
    private BigDecimal transferAmount;
    private String destinationIban;
    private String sourceIban;

    private ContactRequest contactRequest;



}
