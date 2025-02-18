package com.alibou.banking.user;

import com.alibou.banking.account.Account;

import com.alibou.banking.address.Address;
import com.alibou.banking.contact.contact;
import com.alibou.banking.role.Role;

import com.alibou.banking.transaction.Transaction;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<contact> contacts;
    @OneToOne
    private Account account;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    private boolean active;
    @ManyToOne
    private Role role;
    @OneToOne(mappedBy = "user")
    private Address address;

}
