package com.alibou.banking.account;

import com.alibou.banking.address.Address;
import com.alibou.banking.address.AddressMapper;
import com.alibou.banking.address.AddressRepository;
import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.user.User;
import com.alibou.banking.user.UserMapper;
import com.alibou.banking.user.UserRepository;
import com.alibou.banking.user.UserRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final AccountMapper accountMapper;

    @Override
    public void createAccount(CreateAccountRequest accountRequest) {
        UserRequest userRequest = accountRequest.getUser();
        User user = userMapper.mapToUserEntity(userRequest);
        User savedUser = userRepository.save(user);

        AddressRequest addressRequest = accountRequest.getAddress();
        Address address = addressMapper.mapToAddressEntity(addressRequest, savedUser);
        addressRepository.save(address);

        final String iban = generateIban();
        Account account = accountMapper.mapToAccountEntity(iban, savedUser);
        accountRepository.save(account);

    }

    @Override
    public void deactivateAccount(Long accountId) {
        changeStatus(accountId, false);

    }



    @Override
    public void activateAccount(Long accountId) {
        changeStatus(accountId, true);
    }

    private String generateIban() {
        return "TN12 1233 3333 3333 33";
    }


    private void changeStatus(Long accountId, boolean status) {
        Optional<Account> account = accountRepository.findByIdAndIsActive(accountId, !status);
        account.ifPresentOrElse(value -> value.setIsActive(status), () -> {
            log.error("Account not found");
            throw new EntityNotFoundException("Account not found");
        });
    }
}
