package com.alibou.banking.entities.role;

import com.alibou.banking.entities.shared.SharedEntity;

import com.alibou.banking.entities.users.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
public class RoleEntity extends SharedEntity {

    private String name;
    @OneToMany (mappedBy = "RoleEntity")
    private List<Users> users;

}
