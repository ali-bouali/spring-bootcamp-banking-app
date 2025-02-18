package com.alibou.banking.transaction;

import com.alibou.banking.fraud.Fraud;

import com.alibou.banking.user.User;


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private String destinationIban;
    private String sourceIban;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    private Fraud fraud;

}
