package com.alibou.banking.contact;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message="firstname of contact must not be empty")
    private String firstName;
    @NotBlank(message="lastname of contact must not be empty")
    private String lastName;
    @NotBlank(message="Iban of contact must not be empty")
    private String iban;
}
