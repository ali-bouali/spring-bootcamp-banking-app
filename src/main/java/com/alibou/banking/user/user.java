package com.alibou.banking.user;

import com.alibou.banking.account.account;
import com.alibou.banking.address.address;
import com.alibou.banking.contact.contact;
import com.alibou.banking.role.role;
import com.alibou.banking.transaction.transaction;

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
public class user {

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
    private account account;
    @OneToMany(mappedBy = "user")
    private List<transaction> transactions;
    private boolean active;
    @ManyToOne
    private role role;
    @OneToOne(mappedBy = "user")
    private address address;

}
