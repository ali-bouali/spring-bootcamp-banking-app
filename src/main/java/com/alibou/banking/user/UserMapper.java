package com.alibou.banking.user;

import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToUserEntity(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}
