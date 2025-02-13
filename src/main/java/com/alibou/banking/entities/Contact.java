package com.alibou.banking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contacts")
public class Contact {
    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String account_number;
    private String bank_name;

    @ManyToOne
    private User user;
}
