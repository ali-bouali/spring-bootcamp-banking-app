package com.alibou.banking.user;

import com.alibou.banking.account.Account;
import com.alibou.banking.contact.Contact;
import com.alibou.banking.role.Role;
import com.alibou.banking.transaction.Transaction;
import jakarta.persistence.*;
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
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @OneToMany
    private List<Contact> contacts;
    @OneToOne
    private Account account;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;
    private boolean active;
    @ManyToOne
    private Role role;

}
