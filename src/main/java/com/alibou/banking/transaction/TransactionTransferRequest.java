package com.alibou.banking.transaction;

import com.alibou.banking.contact.ContactRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Please add a raison for this transfer")
    private String raison;
    @Positive(message = "the transfer amount should not be null nor negative")
    private BigDecimal transferAmount;
    @NotBlank(message = "The distination Iban should not bu null")
    @Size(min = 34, max = 34, message = "IBAN must be valid")
    private String destinationIban;
    @NotBlank(message = "The source Iban should not bu null")
    @Size(min = 34, max = 34, message = "IBAN must be valid")
    private String sourceIban;
    @Valid
    private ContactRequest contact;
}
