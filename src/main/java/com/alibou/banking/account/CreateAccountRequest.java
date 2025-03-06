package com.alibou.banking.account;

import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.user.UserRequest;
import jakarta.validation.Valid;
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
public class CreateAccountRequest {

    @Valid
    private UserRequest user;

    @Valid
    private AddressRequest address;
}
