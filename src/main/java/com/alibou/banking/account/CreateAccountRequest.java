package com.alibou.banking.account;

import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.user.UserRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateAccountRequest {
    UserRequest userRequest;
    AddressRequest addressRequest;

}
