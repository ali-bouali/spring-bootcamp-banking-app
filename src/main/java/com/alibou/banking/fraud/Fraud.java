package com.alibou.banking.fraud;

import com.alibou.banking.transaction.Transaction;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FRAUD")
public class Fraud {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private FraudType type;
    @Enumerated(EnumType.STRING)
    private FraudStatus status;
    private LocalDateTime date;
    @OneToOne
    private Transaction transaction;


}
