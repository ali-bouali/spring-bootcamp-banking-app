package com.alibou.banking.entites.account;

import com.alibou.banking.entites.user.User;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "ACCOUNTS")
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String iban;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
