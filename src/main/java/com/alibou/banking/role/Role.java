package com.alibou.banking.role;

import com.alibou.banking.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
    private List<User> users;

}
