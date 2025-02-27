package com.alibou.banking.user;

import lombok.Builder;
import org.springframework.stereotype.Service;


@Service
public class UserMapper {

    public User mapToUser(UserRequest userRequest) {
        return User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

}
