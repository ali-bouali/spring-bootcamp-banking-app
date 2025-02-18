package com.alibou.banking.entities.account;


import com.alibou.banking.entities.shared.SharedEntity;
import com.alibou.banking.entities.users.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNTS")
public class AccountEntity extends SharedEntity {
    private String iban;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users users;


}
