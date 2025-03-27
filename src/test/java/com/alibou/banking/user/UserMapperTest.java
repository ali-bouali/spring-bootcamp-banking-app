package com.alibou.banking.user;


import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final UserMapper userMapper= new UserMapper(new BCryptPasswordEncoder());
    PasswordEncoder passwordEncoder=new  BCryptPasswordEncoder();
    @Test
     void user_accout_map_correctly(){
        final UserRequest userRequest = UserRequest.builder()
                .email("email@gmail.com")
                .firstName("walid")
                .lastName("ksouri")
                .password("walid123")
                .build();

        final User user =userMapper.mapToUserEntity(userRequest);


        assertEquals("email@gmail.com",user.getEmail());
        assertEquals("walid",user.getFirstName());
        assertEquals("ksouri",user.getLastName());
       assertTrue(passwordEncoder.matches  ("walid123",user.getPassword() ) );
    }
    @Test
    void should_not_map_to_User_if_userRequest_is_null(){

        final NullPointerException exp=assertThrows(NullPointerException.class,
                ()->userMapper.mapToUserEntity(null));
        assertEquals("userRequest cannot be null", exp.getMessage());



    }

    @Test
    void should__map_to_User_Response_correctly(){
        User user=User.builder()
                .id(1L)
                .firstName("jihen")
                .lastName("mlayeh")
                .email("email@gmail.com")
                .active(true)
                .build();

        UserResponse userResponse=userMapper.mapToUserResponse(user);
        assertEquals(user.getId(),userResponse.getId());
        assertEquals(user.getFirstName(),userResponse.getFirstName());
        assertEquals(user.getLastName(),userResponse.getLastName());
        assertEquals(user.getEmail(),userResponse.getEmail());

    }
    @Test
    void should_not_map_to_User_Response_if_user_is_null(){
        final NullPointerException exp=assertThrows(NullPointerException.class,()->userMapper.mapToUserResponse(null));

    assertEquals("user cannot be null", exp.getMessage());

    }




}
