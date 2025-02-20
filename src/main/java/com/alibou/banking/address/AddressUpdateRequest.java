package com.alibou.banking.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
