package com.alibou.banking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Account {
    @Id
    private String rib;
    private double balance;
    private Date createdAt;
    @ManyToOne
    User user;
}
