package com.alibou.banking.transaction;

import com.alibou.banking.contact.ContactRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
public class TransactionTransferRequest {

    @NotBlank(message = "The reason must not be empty.")
    private String raison;

    @NotNull(message = "Transfer Amount must not be null")
    @Positive(message = "The Transfer amount must be a positive number.")
    private BigDecimal transferAmount;

    @NotBlank(message = "The destination IBAN must not be empty.")
    private String destinationIban;

    @NotBlank(message = "The source IBAN must not be empty.")
    private String sourceIban;

    @Valid
    private ContactRequest contact;
}
