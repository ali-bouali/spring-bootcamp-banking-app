package com.alibou.banking.user;

import com.alibou.banking.exceptions.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public void createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new UserException("Email already exists");
        }
        User user = userMapper.mapToUserEntity(userRequest);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, UserUpdateRequest userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> findAllUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(userMapper::mapToUserResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapToUserResponse)
                .orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public void changePassword(Long userId, ChangePasswordRequest changePasswordRequest) {
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            throw new UserException("Passwords do not match");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));

        // check the current password
        // todo we need to check the crypted password
        if (!user.getPassword().equals(changePasswordRequest.getOldPassword())) {
            throw new UserException("Old password does not match");
        }

        user.setPassword(changePasswordRequest.getNewPassword());
        userRepository.save(user);
    }
}
