package com.alibou.banking.account;

import com.alibou.banking.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue
    private Long id;
    private String iban;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
