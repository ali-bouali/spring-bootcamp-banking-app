package com.alibou.banking.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "frauds")
public class Fraud {
    @Id
    private int id;
    private String reason;
    private String status;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
}
