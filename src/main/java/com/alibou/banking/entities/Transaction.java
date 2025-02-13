package com.alibou.banking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private int id;
    private double amount;
    private String date;
    private String type;

    @ManyToOne
    private User user;
}
