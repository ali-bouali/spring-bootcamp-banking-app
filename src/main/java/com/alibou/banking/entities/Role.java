package com.alibou.banking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private int id;
    private String role;
    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
