package com.alibou.banking.user;

import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.role.RoleRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
    private UserRequest userRequest;
    private AddressRequest addressRequest;
    private RoleRequest roleRequest;

}
