package com.alibou.banking.entites.user;

import com.alibou.banking.entites.account.Account;
import com.alibou.banking.entites.address.Address;
import com.alibou.banking.entites.contact.Contact;
import com.alibou.banking.entites.role.Role;
import com.alibou.banking.entites.transaction.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private List<Contact> contacts;
    @OneToOne
    private Account account;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    private boolean active;
    @ManyToOne
    private Role role;
    @OneToOne
    private Address address;

}
