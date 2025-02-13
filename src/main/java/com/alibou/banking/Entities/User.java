package com.alibou.banking.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="appUser")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String fullName;
    private String phone;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "user")
    private List<Contact>contacts;
    @OneToOne
    @JoinColumn(name="accountId")
    private Account account;
    @OneToOne
    @JoinColumn(name="roleId")
    private Role role;
}
