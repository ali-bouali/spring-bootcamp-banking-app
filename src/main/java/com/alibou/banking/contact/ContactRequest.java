package com.alibou.banking.contact;

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
    private Long userId;

}
