package com.alibou.banking.Contact;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRequest {
    private String firstName;
    private String lastName;
    private String iban;


}
