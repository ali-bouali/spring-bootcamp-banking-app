package com.alibou.banking.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;
    private Double transactionAmount;
    private String description;


    @ManyToOne
    private User user_t;

    @OneToOne
    private Fraud fraud;

}
