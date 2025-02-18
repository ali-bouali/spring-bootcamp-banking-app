package com.alibou.banking.entities.users;

import com.alibou.banking.entities.account.AccountEntity;
import com.alibou.banking.entities.address.AddressEntity;
import com.alibou.banking.entities.contact.ContactEntity;
import com.alibou.banking.entities.role.RoleEntity;
import com.alibou.banking.entities.shared.SharedEntity;
import com.alibou.banking.entities.transaction.TransactionEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class Users extends SharedEntity {

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToMany(mappedBy = "users")
    private List<ContactEntity> ContactEntities;
    @OneToOne
    private AccountEntity AccountEntity;
    @OneToMany(mappedBy = "users")
    private List<TransactionEntity> TransactionEntities;
    private boolean active;
    @ManyToOne
    private RoleEntity RoleEntity;
    @OneToOne(mappedBy = "users")
    private AddressEntity AddressEntity;

}
