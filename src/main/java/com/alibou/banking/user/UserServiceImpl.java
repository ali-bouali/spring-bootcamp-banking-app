package com.alibou.banking.user;

import com.alibou.banking.address.Address;
import com.alibou.banking.address.AddressMapper;
import com.alibou.banking.address.AddressRepository;
import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.role.Role;
import com.alibou.banking.role.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    private final AddressRepository addressRepository;

    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public void createUser(CreateUserRequest userRequest) {

        User userToSave = userMapper.mapToUserEntity(userRequest.getUser());
        Optional<Role> role = roleRepository.findById(userRequest.getRole().getId());
        role.ifPresent(userToSave::setRole);

        userRepository.save(userToSave);

        AddressRequest addressRequest = userRequest.getAddress();
        Address address = addressMapper.mapToAddressEntity(addressRequest, userToSave);
        addressRepository.save(address);
    }

    @Override
    public void UpdateUser(CreateUserRequest userRequest) {
         Optional<User> user= userRepository.findById(userRequest.getUser().getId()) ;
    if (user.isEmpty())
    {
        throw new EntityNotFoundException("user not found");
    }

        User userToUpdate = user.get();
        userToUpdate.setFirstName(userRequest.getUser().getFirstName());
        userToUpdate.setLastName(userRequest.getUser().getLastName());
        userToUpdate.setEmail(userRequest.getUser().getEmail());
        userToUpdate.setPassword(userRequest.getUser().getPassword());
        Optional<Role> role =  roleRepository.findById(userRequest.getRole().getId());
        role.ifPresent(userToUpdate::setRole);
        //on a utilisé
        userToUpdate.setAddress(addressMapper.mapToAddressEntity(userRequest.getAddress(), userToUpdate));
        userRepository.save(userToUpdate);
        }




}
