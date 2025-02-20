package com.alibou.banking.role.service;

import com.alibou.banking.role.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getRoles();

    void saveOrUpdate(Role role);

    void deleteRole(Role role);

    Role getRole(Long id) throws RuntimeException;


}
