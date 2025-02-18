package com.alibou.banking.account;


import com.alibou.banking.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String iban;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


}
