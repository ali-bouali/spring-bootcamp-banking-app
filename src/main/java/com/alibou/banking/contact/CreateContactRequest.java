package com.alibou.banking.contact;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateContactRequest {
    private String firstName;
    private String lastName;
    private String iban;
    private Long userId;

}
