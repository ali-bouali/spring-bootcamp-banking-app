package com.alibou.banking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    private String role_name;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
