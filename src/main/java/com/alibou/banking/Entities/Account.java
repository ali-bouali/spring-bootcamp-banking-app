package com.alibou.banking.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Account {
    @Id
    @GeneratedValue
    private int accountId;
    private String accountNumber;
    private String accountType;
    private double accountBalance;
    private String accountStatus;
    private String creationDate;
    @OneToOne(mappedBy = "account")
    private User user;

}
