package com.alibou.banking.address;

import com.alibou.banking.user.user;

import com.alibou.banking.common.AbstractEntity;
import com.alibou.banking.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "ADDRESSES")
public class Address extends AbstractEntity {

    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
