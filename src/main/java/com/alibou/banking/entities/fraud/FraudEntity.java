package com.alibou.banking.entities.fraud;

import com.alibou.banking.entities.shared.SharedEntity;
import com.alibou.banking.entities.transaction.TransactionEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FRAUDS")
public class FraudEntity extends SharedEntity {
    @Enumerated(EnumType.STRING)
    private FraudType type;
    @Enumerated(EnumType.STRING)
    private FraudStatus status;
    private LocalDateTime date;
    @OneToOne
    private TransactionEntity TransactionEntity;


}
