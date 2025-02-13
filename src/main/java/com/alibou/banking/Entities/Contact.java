package com.alibou.banking.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Contact {
    @Id
    @GeneratedValue
    private int contactId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}
