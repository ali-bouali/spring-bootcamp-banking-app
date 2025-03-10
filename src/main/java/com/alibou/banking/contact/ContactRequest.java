package com.alibou.banking.contact;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequest {
    @NotBlank(message = "Contact first name must not be empty")
    private String firstName;
    @NotBlank(message = "Contact last name must not be empty")
    private String lastName;
    @NotBlank(message = "Contact last name must not be empty")
    @Size(min = 34, max = 34, message = "IBAN must be exactly 34 characters long")
    @Pattern(regexp = "^[A-Z0-9]+$", message = "IBAN must contain only uppercase letters and numbers")
    private String iban;
}
