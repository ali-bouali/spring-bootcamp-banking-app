package com.alibou.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Frauds")
public class Fraud {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Transaction transaction;
}
