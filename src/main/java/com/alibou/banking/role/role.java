package com.alibou.banking.role;

import com.alibou.banking.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ROLE")
public class role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany
    private List<User> users;

}
