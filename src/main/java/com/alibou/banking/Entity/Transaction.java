package com.alibou.banking.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private long id;
    private TypeTrans Type;
    private String description;
    private Date date;
    private double amount;

    @ManyToOne
    private Fraud fraud;

    @ManyToOne
    private Account account;

}
