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
@Table(name = "CONTACTS")
public class Contact {

    @Id
    @GeneratedValue
    private Integer id;

    private String phoneNumber;

    @ManyToOne
    private User user;
}
