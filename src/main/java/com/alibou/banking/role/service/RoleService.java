package com.alibou.banking.role.service;

import com.alibou.banking.role.Role;

import java.util.List;

public interface RoleService {

   List<RoleResponse> getRoles();

    void  createRole( RoleRequest roleRequest , Long id);

}
