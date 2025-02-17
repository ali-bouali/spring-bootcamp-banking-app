package com.alibou.banking.models.user;

import com.alibou.banking.models.account.account;
import com.alibou.banking.models.address.address;
import com.alibou.banking.models.contact.contact;
import com.alibou.banking.models.role.role;
import com.alibou.banking.models.transaction.transaction;

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
@Table(name = "users")
public class user {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(mappedBy = "users")
    private List<contact> contacts;
    @OneToOne
    private account account;
    @OneToMany(mappedBy = "users")
    private List<transaction> transactions;
    private boolean active;
    @ManyToOne
    private role role;
    @OneToOne(mappedBy = "users")
    private address address;

}
