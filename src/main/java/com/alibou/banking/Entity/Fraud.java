package com.alibou.banking.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fraud {
    @Id
    @GeneratedValue
    private long id;
    private Integer frau;

    private String description;

    @OneToMany(mappedBy = "Fraud")
    private List<Transaction> transaction;

}
