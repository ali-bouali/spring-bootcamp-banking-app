package com.alibou.banking.role;

import com.alibou.banking.common.AbstractEntity;
import com.alibou.banking.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
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
@Table(name = "ROLES")
public class Role extends AbstractEntity {

    private String name;
    @OneToMany(mappedBy = "role")
    private List<User> users;

}
