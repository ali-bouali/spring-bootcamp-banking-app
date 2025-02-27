package com.alibou.banking.address;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder

public class AddressRequest {
    private String steet;
    private String city;
    private String state;
    private String postalCode;
    private String country;


}
