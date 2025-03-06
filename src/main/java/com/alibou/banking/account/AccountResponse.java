package com.alibou.banking.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountResponse {

    private Long id;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String accountIban;
    private LocalDateTime createdAt;
    private boolean locked;
}
