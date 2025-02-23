package com.alibou.banking.user;

import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.role.service.RoleRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.bind.annotation.BindParam;

@Data
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    private UserRequest user;
    private AddressRequest address;
    private RoleRequest role;
}
