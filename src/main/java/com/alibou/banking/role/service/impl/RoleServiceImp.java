package com.alibou.banking.role.service.impl;

import com.alibou.banking.role.Role;
import com.alibou.banking.role.RoleRepository;
import com.alibou.banking.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {
    private final   RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        return   roleRepository.findAll();
    }

    @Override
    public void saveOrUpdate(Role role) {
        if( role.getId() != null && roleRepository.existsById(role.getId()))
        {
            var  editRole = getRole(role.getId());
            editRole.setName(role.getName());
            editRole.setUsers(role.getUsers());
            roleRepository.save(editRole);
        }else {
            roleRepository.save(role);
        }
    }

    @Override
    public void deleteRole(Role role) {
        if( role.getId() != null && !roleRepository.existsById(role.getId())){
            throw new RuntimeException("role not found");
        }
        roleRepository.delete(role);
    }

    @Override
    public Role getRole(Long id)  throws RuntimeException{
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("role not found with id : " + id));
    }

}
