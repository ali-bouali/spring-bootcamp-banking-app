package com.alibou.banking.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void createUser(UserRequest user);
    void updateUser(Long userId, UserUpdateRequest user);
    List<UserResponse> findAllUsers(int page, int size);
    UserResponse findById(Long userId);
    void changePassword(Long userId, ChangePasswordRequest changePasswordRequest);

}
