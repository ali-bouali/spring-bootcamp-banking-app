package com.alibou.banking.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class RoleService {


    private final RoleRepository roleRepository;



    Role getRoleByName(String roleName) {
        Optional<Role> role=roleRepository.findByName(roleName);
        return role.orElse(null);
    }


    Role updateRole(Long idRole, String roleName) {
        Optional<Role> role=roleRepository.findById(idRole);
        if(role.isEmpty()){
            return null;
        }
        role.ifPresent(value -> value.setName(roleName));
        roleRepository.save(role.get());
        return role.get();
    }
}