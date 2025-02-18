package com.alibou.banking.entities.transaction;

import com.alibou.banking.entities.fraud.FraudEntity;
import com.alibou.banking.entities.shared.SharedEntity;
import com.alibou.banking.entities.users.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTIONS")
public class TransactionEntity extends SharedEntity {

    private String description;
    private BigDecimal amount;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private String destinationIban;
    private String sourceIban;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @OneToOne
    private FraudEntity FraudEntity;

}
