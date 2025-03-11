package com.alibou.banking.transaction;

import com.alibou.banking.contact.ContactRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
public class TransactionTransferRequest {

    private String raison;
    @Positive(message = "amount must be positive")
    private BigDecimal transferAmount;
    @NotBlank(message="destination iban must not be empty")
    private String destinationIban;
    @NotBlank(message="source iban must not be empty")
    private String sourceIban;
    @Valid
    private ContactRequest contact;
}