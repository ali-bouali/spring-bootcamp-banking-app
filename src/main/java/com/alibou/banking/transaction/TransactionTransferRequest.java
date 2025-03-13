package com.alibou.banking.transaction;

import com.alibou.banking.contact.ContactRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotNull
    @NotEmpty(message = "reason must not be empty.")
    @NotBlank(message = "reason Name must not be empty")
    private String raison;
    @NotNull(message = "the amount must be not null")
    @Positive(message = "the amount should be positive")
    private BigDecimal transferAmount;
    @NotNull
    @NotEmpty(message = "Iban must not be empty.")
    @NotBlank(message = "Iban Name must not be empty")
    private String destinationIban;
    @NotNull
    @NotEmpty(message = "Iban must not be empty.")
    @NotBlank(message = "Iban Name must not be empty")
    private String sourceIban;
    @Valid
    private ContactRequest contact;
}
