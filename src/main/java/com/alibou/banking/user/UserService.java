package com.alibou.banking.user;

import com.alibou.banking.address.Address;
import com.alibou.banking.role.Role;
import com.alibou.banking.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public User SignIn(String email, String password) {
     User user=   userRepository.findUserByEmailAndPassword(email,password);
        if(user==null){
            throw new RuntimeException("User does not exist with email: " + email);
        }
        return user;
    }


    public User getUserByEmail(String email) {
       Optional<User> user =userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public void updateUser(String userEmail,UserRequestUpdate userRequestUpdate) {
        User user=getUserByEmail(userEmail);
        if(user==null){
            throw new RuntimeException("User does not exist with email: " + userEmail);
        }
        if(userRequestUpdate==null){
            return ;
        }
        if(userRequestUpdate.getEmail()!=null){
            if(userRepository.existsByEmail(userRequestUpdate.getEmail())){
                throw new RuntimeException("Email already exists with email: " + userRequestUpdate.getEmail());
            }
            user.setEmail(userRequestUpdate.getEmail());
        }
        if(userRequestUpdate.getFirstName()!=null){
            user.setFirstName(userRequestUpdate.getFirstName());
        }
        if(userRequestUpdate.getLastName()!=null){
            user.setLastName(userRequestUpdate.getLastName());
        }
        if(userRequestUpdate.getAddress()!=null){
            Address newAddress=new Address();
            if(userRequestUpdate.getAddress().getCity()!=null){
                newAddress.setCity(userRequestUpdate.getAddress().getCity());
            }
            if(userRequestUpdate.getAddress().getState()!=null){
                newAddress.setState(userRequestUpdate.getAddress().getState());
            }
            if(userRequestUpdate.getAddress().getCountry()!=null){
                newAddress.setCountry(userRequestUpdate.getAddress().getCountry());
            }
            if(userRequestUpdate.getAddress().getPostalCode()!=null){
                newAddress.setPostalCode(userRequestUpdate.getAddress().getPostalCode());
            }
            user.setAddress(newAddress);
        }
        userRepository.save(user);

    }

    public void createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }
         userRepository.save(user);
    }

    public void updateUserStatus(String emailAdmin,String emailUser, Boolean status) {
        User admin=getUserByEmail(emailAdmin);
        if(admin==null ){
            throw new RuntimeException("Admin does not exist with email: " + emailAdmin);
        }
        if(!admin.getRole().getName().equals("admin")){
            throw new RuntimeException("you don't have permission to update user status: " + emailAdmin);
        }
        User user=getUserByEmail(emailUser);
        if(user==null){
            throw new RuntimeException("User does not exist with email: " + emailUser);
        }
        user.setActive(status);
        userRepository.save(user);
    }

    public void updateUserRole(String emailAdmin,String emailUser, String role) {
        User admin=getUserByEmail(emailAdmin);
        if(admin==null ){
            throw new RuntimeException("Admin does not exist with email: " + emailAdmin);
        }
        if(!admin.getRole().getName().equals("admin")){
            throw new RuntimeException("you don't have permission to update user role: " + emailAdmin);
        }
        User user=getUserByEmail(emailUser);
        if(user==null){
            throw new RuntimeException("User does not exist with email: " + emailUser);
        }
        Optional<Role> newRole=roleRepository.findByName(role);
        user.setRole(newRole.orElse(null));
        userRepository.save(user);
    }
}