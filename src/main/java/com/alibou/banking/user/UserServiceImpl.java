package com.alibou.banking.user;

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
            throw new RuntimeException("Email already exists");
        }
        User user = userMapper.mapToUserEntity(userRequest);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long userId, UserUpdateRequest userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
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
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
