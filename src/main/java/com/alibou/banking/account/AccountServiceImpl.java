package com.alibou.banking.account;

import com.alibou.banking.address.Address;
import com.alibou.banking.address.AddressMapper;
import com.alibou.banking.address.AddressRepository;
import com.alibou.banking.address.AddressRequest;
import com.alibou.banking.role.Role;
import com.alibou.banking.role.RoleName;
import com.alibou.banking.role.RoleRepository;
import com.alibou.banking.user.User;
import com.alibou.banking.user.UserMapper;
import com.alibou.banking.user.UserRepository;
import com.alibou.banking.user.UserRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public void createAccount(CreateAccountRequest accountRequest) {
        UserRequest userRequest = accountRequest.getUser();
        User user = userMapper.mapToUserEntity(userRequest);

        Role customerRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER.name())
                .orElseThrow(() -> new EntityNotFoundException(RoleName.ROLE_CUSTOMER.name()));

        user.setRole(customerRole);

        User savedUser = userRepository.save(user);

        AddressRequest addressRequest = accountRequest.getAddress();
        Address address = addressMapper.mapToAddressEntity(addressRequest, savedUser);
        addressRepository.save(address);

        final String iban = generateIban();
        Account account = accountMapper.mapToAccountEntity(iban, savedUser);
        accountRepository.save(account);

        savedUser.setAccount(account);
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void lockAccount(Long accountId) {
        accountRepository.lockAccount(accountId);
    }

    @Override
    @Transactional
    public void unlockAccount(Long accountId) {
        accountRepository.unlockAccount(accountId);
    }

    @Override
    public List<AccountResponse> findAllAccounts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return accountRepository.findAll(pageRequest)
                .getContent()
                .stream()
                .map(accountMapper::mapToAccountResponse)
                .toList();
    }

    @Override
    public AccountResponse findAccountById(Long accountId) {
        return accountRepository.findById(accountId)
                .map(accountMapper::mapToAccountResponse)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with id: " + accountId));
    }

    private String generateIban() {
        final String newIban = Iban.random(CountryCode.TN)
                .toFormattedString();

        boolean ibanAlreadyExists = accountRepository.existsByIban(newIban);
        if (ibanAlreadyExists) {
            generateIban();
        }
        return newIban;
    }
}
