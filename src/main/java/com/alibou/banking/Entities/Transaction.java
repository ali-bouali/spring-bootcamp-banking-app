package com.alibou.banking.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private int transactionId;
    private int amount;
    private String description;
    private String date ;
    private String status ;
    private String sourceAccount;
    private String destinationAccount;
@ManyToOne
@JoinColumn(name="userId")
    private User user;
@OneToOne
@JoinColumn(name="fraudId")
    private Fraud fraud;

}
