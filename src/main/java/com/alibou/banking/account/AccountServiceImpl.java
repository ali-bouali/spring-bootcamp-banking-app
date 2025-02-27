package com.alibou.banking.account;

import com.alibou.banking.address.Address;
import com.alibou.banking.address.AddressMapper;
import com.alibou.banking.address.AdressRepository;
import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.user.User;
import com.alibou.banking.user.UserMapper;
import com.alibou.banking.user.UserRepository;
import com.alibou.banking.user.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AdressRepository adressRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final AccountMapper accountMapper;



    @Override
    public void createAccount(CreateAccountRequest accountRequest) {
        UserRequest userRequest=accountRequest.getUserRequest();
        User user= userMapper.mapToUser(userRequest);
        User savedUser=userRepository.save(user);

        AddressRequest addressRequest=accountRequest.getAddressRequest();
        Address address =addressMapper.mapToAddress(addressRequest, savedUser);
        adressRepository.save(address);

        Account account = accountMapper.mapToAccount(savedUser,generateIban());

    }

    @Override
    public void lockAccount(Long accountId) {
        accountRepository.lockAccount(accountId);
    }

    @Override
    public void unlockAccount(Long accountId) {
        accountRepository.unlockAccount(accountId);
    }
    private String generateIban() {
        return "TN12 1233 3333 3333 33";
    }
}
