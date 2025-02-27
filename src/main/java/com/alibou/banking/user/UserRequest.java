package com.alibou.banking.user;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;


}
