package com.alibou.banking.address;

import com.alibou.banking.user.User;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {
    public Address mapToAddressEntity(AddressRequest addressRequest, User user) {
        return Address.builder()
                .country(addressRequest.getCountry())
                .city(addressRequest.getCity())
                .street(addressRequest.getStreet())
                .postalCode(addressRequest.getPostalCode())
                .user(user)
                .build();
    }
}
