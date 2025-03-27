package com.alibou.banking.account;

import com.alibou.banking.address.Address;
import com.alibou.banking.address.AddressMapper;
import com.alibou.banking.address.AddressRepository;
import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.role.Role;
import com.alibou.banking.role.RoleName;
import com.alibou.banking.role.RoleRepository;
import com.alibou.banking.user.*;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private AddressMapper addressMapper;
    @Mock
    private AccountMapper accountMapper;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void should_create_account_successfully() {
        final CreateAccountRequest createAccountRequest = new CreateAccountRequest();

        final UserRequest createUserRequest = buildUserRequest();
        createAccountRequest.setUser(createUserRequest);

        final AddressRequest addressRequest = buildAddressRequest();
        createAccountRequest.setAddress(addressRequest);

        final User mappedUser = buildMapperUser();

        final Address mappedAddress = buildMappedAddress(mappedUser);

        // preparation of calls
        prepareMocks(createUserRequest, mappedUser, addressRequest, mappedAddress);

        // execute the method
        accountService.createAccount(createAccountRequest);

        // assertions
        assertions(mappedUser, mappedAddress);
    }

    private void assertions(User mappedUser, Address mappedAddress) {
        verify(userRepository, times(2)).save(mappedUser);
        verify(addressRepository, times(1)).save(mappedAddress);
        verify(accountRepository, times(1)).save(any());
    }

    private void prepareMocks(UserRequest createUserRequest, User mappedUser, AddressRequest addressRequest, Address mappedAddress) {
        when(userMapper.mapToUserEntity(createUserRequest))
                .thenReturn(mappedUser);

        final Role customerRole = Role.builder()
                .name(RoleName.ROLE_CUSTOMER.name())
                .build();
        when(roleRepository.findByName(RoleName.ROLE_CUSTOMER.name()))
                .thenReturn(Optional.of(customerRole));

        when(addressMapper.mapToAddressEntity(addressRequest, mappedUser))
                .thenReturn(mappedAddress);

        when(userRepository.save(mappedUser)).thenReturn(mappedUser);
    }

    @Test
    void should_throw_entity_not_found_exception_if_role_not_found() {
        final CreateAccountRequest createAccountRequest = new CreateAccountRequest();

        final UserRequest createUserRequest = buildUserRequest();
        createAccountRequest.setUser(createUserRequest);

        final AddressRequest addressRequest = buildAddressRequest();
        createAccountRequest.setAddress(addressRequest);

        final User mappedUser = buildMapperUser();


        // preparation of calls
        when(userMapper.mapToUserEntity(createUserRequest))
                .thenReturn(mappedUser);
        when(roleRepository.findByName(any()))
                .thenThrow(new EntityNotFoundException("No role found with name : ROLE_CUSTOMER"));

        // execute the method && assert throws
        final EntityNotFoundException exp = assertThrows(EntityNotFoundException.class,
                () -> accountService.createAccount(createAccountRequest));
        assertEquals("No role found with name : ROLE_CUSTOMER", exp.getMessage());

    }

    private static Address buildMappedAddress(User mappedUser) {
        return Address.builder()
                .city("city")
                .country("country")
                .street("street")
                .postalCode("postalCode")
                .state("state")
                .user(mappedUser)
                .build();
    }

    private static User buildMapperUser() {
        return User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .password("password")
                .build();
    }

    private static AddressRequest buildAddressRequest() {
        final AddressRequest addressRequest = AddressRequest.builder()
                .city("city")
                .country("country")
                .street("street")
                .postalCode("postalCode")
                .state("state")
                .build();
        return addressRequest;
    }

    private static UserRequest buildUserRequest() {
        final UserRequest createUserRequest = UserRequest.builder()
                .firstName("firstName")
                .lastName("lastName")
                .email("email@mail.com")
                .password("password")
                .build();
        return createUserRequest;
    }

}