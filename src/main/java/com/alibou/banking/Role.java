package com.alibou.banking;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Role {
    @Id
    private Integer id;
    private String roleName;
    @OneToMany(mappedBy = "role")
    List<User> users;

}
