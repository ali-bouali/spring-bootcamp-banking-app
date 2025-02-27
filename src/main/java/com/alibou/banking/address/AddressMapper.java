package com.alibou.banking.address;

import com.alibou.banking.user.User;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {
    public Address  mapToAddress(AddressRequest addressRequest, User user){
        return Address.builder()
                .street(addressRequest.getSteet())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .postalCode(addressRequest.getPostalCode())
                .country(addressRequest.getCountry())
                .user(user)
                .build();
    }
}
