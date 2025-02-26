package com.alibou.banking.user;

public interface UserService {
    void createUser(CreateUserRequest userRequest);
    void updateUser(UserRequest userRequest);

}
