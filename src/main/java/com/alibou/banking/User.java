package com.alibou.banking;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
    private String password;
    @ManyToOne
    Role role;
    @OneToMany(mappedBy = "user")
    List<Contact> contacts;
    @OneToMany(mappedBy = "user")
    List<Account> account;
    @OneToMany(mappedBy="user")
    List<Transaction>transactions;
}