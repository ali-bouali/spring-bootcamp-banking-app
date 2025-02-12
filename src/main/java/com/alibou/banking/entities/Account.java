package com.alibou.banking.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String password;
    private Double amount_of_money;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;




}
