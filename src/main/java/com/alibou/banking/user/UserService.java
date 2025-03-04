package com.alibou.banking.user;

import java.util.List;

public interface UserService {

    void createUser(UserRequest user);
    void updateUser(Long userId, UserUpdateRequest user);
    List<UserResponse> findAllUsers(int page, int size);
    UserResponse findById(Long userId);

}
