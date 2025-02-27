package com.alibou.banking.Contact;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String iban;


}



