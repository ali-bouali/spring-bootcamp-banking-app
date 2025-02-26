package com.alibou.banking.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public void createUser(CreateUserRequest userRequest) {
        User user = userMapper.mapToUserEntity(userRequest.getUserRequest());


    }

    @Override
    public void updateUser(UserRequest userRequest) {

    }
}
