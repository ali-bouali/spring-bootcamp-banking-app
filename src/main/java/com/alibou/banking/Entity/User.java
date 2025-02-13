package com.alibou.banking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    @OneToOne
    private Account account;
    @OneToMany(mappedBy = "user")
    private List<Role> roles;
    @OneToMany(mappedBy = "userc")
    private List<Contact> contacts;

}
