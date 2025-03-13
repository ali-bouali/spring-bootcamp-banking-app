package com.alibou.banking.contact;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequest {
    @NotNull
    @NotEmpty(message = "First name must not be empty.")
    @NotBlank(message = "first Name must not be empty")
    private String firstName;
    @NotNull
    @NotEmpty(message = "Last name must not be empty.")
    @NotBlank(message = "last Name must not be empty")
    private String lastName;
    @NotNull
    @NotEmpty(message = "Iban must not be empty.")
    @NotBlank(message = "Iban Name must not be empty")
    private String iban;
}
