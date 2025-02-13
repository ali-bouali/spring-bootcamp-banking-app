package com.alibou.banking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    private int id;
    private int account_number;
    private String account_type;
    private String account_balance;
    private String opening_date;
    private String closing_date;
}
