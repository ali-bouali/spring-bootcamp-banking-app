package com.alibou.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;
    private String first_name;
    private String last_name;
    private String date_of_birth;
    private String gender;
    private String address;
    private String city;
    @Column(name = "postal")
    private String postal_code;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne
    private Account accounts;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private  List<Contact> contacts;
}
