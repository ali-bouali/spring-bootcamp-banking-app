package com.alibou.banking.user;

import com.alibou.banking.address.Address;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestUpdate {
    private String firstName;
    private String lastName;
    private String email;
    private Address address;

}
