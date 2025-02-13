package com.alibou.banking.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Join;

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
    @JoinColumn(name="user_id")
    private User user_t;

    @OneToOne
    @JoinColumn(name = "fraud_id")
    private Fraud fraud;

}
