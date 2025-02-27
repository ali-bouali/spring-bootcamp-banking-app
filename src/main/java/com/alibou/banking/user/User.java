package com.alibou.banking.user;

import com.alibou.banking.account.Account;
import com.alibou.banking.address.Address;
import com.alibou.banking.common.AbstractEntity;
import com.alibou.banking.contact.Contact;
import com.alibou.banking.role.Role;
import com.alibou.banking.transaction.Transaction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "USERS")
public class User extends AbstractEntity {

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
    @OneToOne(mappedBy = "user")
    private Address address;

}
