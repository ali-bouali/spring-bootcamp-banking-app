package com.alibou.banking.address;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequest {

    @NotBlank(message = "Street must not be empty")
    private String street;
    @NotBlank(message = "City must not be empty")
    private String city;
    @NotBlank(message = "State must not be empty")
    private String state;
    @NotBlank(message = "Postal code must not be empty")
    private String postalCode;
    @NotBlank(message = "Country must not be empty")
    private String country;
}
