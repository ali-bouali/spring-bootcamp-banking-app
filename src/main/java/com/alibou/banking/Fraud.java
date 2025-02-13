package com.alibou.banking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Fraud {
    @Id
    private Integer id;
    private Date date;
    @OneToOne
    Transaction transaction;
    private String description;
}