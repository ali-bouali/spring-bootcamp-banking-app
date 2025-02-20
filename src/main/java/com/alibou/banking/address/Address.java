package com.alibou.banking.address;


import com.alibou.banking.common.AbstractEntity;
import com.alibou.banking.user.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Embeddable
public class Address  {

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

}
