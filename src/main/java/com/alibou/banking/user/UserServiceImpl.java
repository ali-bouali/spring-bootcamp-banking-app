package com.alibou.banking.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public void createUser(UserRequest userRequest) {
        User user=userMapper.mapToUserEntity(userRequest);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserRequest userRequest,Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));

        userMapper.merge(userRequest,user);
        userRepository.save(user);

    }

    @Override
    public void activateUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setActive(true);
    }

    @Override
    public void deactivateUser(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User not found"));
        user.setActive(false);
    }

    @Override
    public List<UserResponse> getAllUser(int size,int page) {
        PageRequest pageRequest=PageRequest.of(page,size);
        return userRepository.findAll(pageRequest).stream().map(userMapper::convertToUserResponse).collect(Collectors.toList());
    }
}
