package com.alibou.banking.role.service;

import com.alibou.banking.role.Role;
import com.alibou.banking.role.RoleRepository;
import com.alibou.banking.user.UserRequest;
import com.alibou.banking.user.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {


    private final  RoleRepository roleRepository;


    @Override
    public List<RoleResponse> getRoles() {

       List<Role> role = roleRepository.findAll();
        List<RoleResponse> rolesResponse =new ArrayList<>() ;

        role.forEach(role1 -> {
            List<UserResponse> usersResponse =new ArrayList<>() ;
            role1.getUsers().forEach(user -> {
                usersResponse.add(UserResponse.builder().id(user.getId()).firstName(user.getFirstName()).lastName(user.getLastName()).build());
            } ) ;
            RoleResponse roleResponse = RoleResponse.builder().name(role1.getName()).users(usersResponse).build();
            rolesResponse.add(roleResponse);
        });
       return  rolesResponse ;
    }

    @Override
    public void createRole(RoleRequest roleRequest ,Long id ) {

        Optional<Role> role = roleRepository.findByName(roleRequest.getName());
         if(role.isPresent())
         {
             throw  new RuntimeException("Role already exists");
         }
         Role saveRole = Role.builder().name(roleRequest.getName()).createdAt(LocalDateTime.now()).createdBy(id).build();
        roleRepository.save(saveRole);
    }


}
