package com.alibou.banking.user;

import com.alibou.banking.account.AccountServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);
    @Mock
    private  UserRepository userRepository;
    @Mock
    private  UserMapper userMapper;


    @InjectMocks
    private UserServiceImpl  userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void should_create_user_correctly(){

        UserRequest userRequest = UserRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .password("password")
                .build();
        User user =User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .password("password")
                .build();

        //mocking

        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(false);
        when (userMapper.mapToUserEntity(userRequest)).thenReturn(user);

        when(userRepository.save(user)).thenReturn(user);

        //execution
        userService.createUser(userRequest);

        //assertion

        assertEquals("firstName", user.getFirstName());
        assertEquals("lastName", user.getLastName());
        assertEquals("email@mail.com", user.getEmail());
        assertEquals(userRequest.getPassword(), user.getPassword());

        verify(userRepository ,times(1)).save(user);
        verify(userMapper,times(1)).mapToUserEntity(userRequest);





    }

    //when(userRepository.findByEmail(userRequest.getEmail())).thenThrow(new UsernameNotFoundException("User not found"));
      /*final UsernameNotFoundException exp=assertThrows(UsernameNotFoundException.class,
                ()->userService.createUser(userRequest));

        assertEquals("User not found",exp.getMessage());*/

    @Test
    void should_not_create_user_with_null_Request(){


        //when(userRepository.findByEmail(any())).thenThrow(new NullPointerException("userRequest is null"));

        final NullPointerException exp=assertThrows(NullPointerException.class,
                ()->userService.createUser(null));

        assertEquals("userRequest is null",exp.getMessage());

    }

    @Test
    void should_not_create_user_with_existing_mail(){

        UserRequest userRequest = UserRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .password("password")
                .build();


        when(userRepository.existsByEmail(userRequest.getEmail())).thenReturn(true);
        final RuntimeException exp =assertThrows(RuntimeException.class,
                ()->userService.createUser(userRequest));

        assertEquals("Email already exists",exp.getMessage());
    }



    @Test
    void update_user_correctly(){
        UserUpdateRequest userRequest=UserUpdateRequest.builder()
                .firstName("walid")
                .lastName("ksouri")
                .build();

        User user =User.builder()
                .id(1L)
                .firstName("jihen")
                .lastName("mlayeh")
                .email("email@mail.com")
                .password("password")
                .build();



        //mocking
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        when(userRepository.save(user)).thenReturn(user);

        log.info(user.getFirstName());
        log.info(user.getLastName());
        //execute the method
        userService.updateUser(1L,userRequest);
        log.info(user.getFirstName());
        log.info(user.getLastName());


        //
        assertEquals("walid", user.getFirstName());
        assertEquals("ksouri", user.getLastName());




    }
    @Test
    void update_user_that_not_exist(){

        final UserUpdateRequest userRequest=UserUpdateRequest.builder()
                .firstName("walid")
                .lastName("ksouri")
                .build();

        when(userRepository.findById(1L)).thenThrow(new RuntimeException("walid"));

        //execute the method




        RuntimeException exp= assertThrows(RuntimeException.class,
                ()->userService.updateUser(1L,userRequest));
        assertEquals("walid",exp.getMessage());



    }



    @Test
    void find_all_users_correctly(){

        User user =User.builder()
                .id(1L)
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .password("password")
                .build();

        User user1 =User.builder()
                .id(2L)
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .password("password")
                .build();
        User user2 =User.builder()
                .id(3L)
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .password("password")
                .build();
        PageRequest pageRequest=PageRequest.of(0,10);
        List<User> list=List.of(user,user1,user2);
        Page<User> page=new PageImpl<>(list, pageRequest, list.size());
        //mocking
        when(userRepository.findAll(pageRequest)).thenReturn(page);


        userService.findAllUsers(0,10);

        assertEquals(3,list.size());

    }


}
