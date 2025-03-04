package com.alibou.banking.user;

import java.util.List;

public interface UserService {
    void createUser(UserRequest userRequest);
    void updateUser(UserRequest userRequest,Long userId);
    void activateUser(Long userId);
    void deactivateUser(Long userId);
    List<UserResponse> getAllUser(int page,int size);



}
