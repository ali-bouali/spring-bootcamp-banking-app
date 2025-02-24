package com.alibou.banking.contact;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateContactRequest {
    private String firstName;
    private String lastName;
    private String iban;
}
