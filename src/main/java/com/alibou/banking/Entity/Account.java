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
public class Account {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique=true)
    private String username;
    private String password;
    private String rib;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;



}
