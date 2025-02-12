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
@Table(name = "ACCOUNTS")
public class Account {

    @Id
    @GeneratedValue
    private Integer rib;

    private String accountName;

    @OneToOne
    private User user;

}
