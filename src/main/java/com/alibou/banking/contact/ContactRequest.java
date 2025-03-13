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

    @NotBlank(message = "first Name must not be empty")
    private String firstName;
    @NotBlank(message = "last Name must not be empty")
    private String lastName;
    @NotBlank(message = "iban must not be empty")
    private String iban;
}
