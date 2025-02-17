package com.alibou.banking.role;

import com.alibou.banking.user.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<role, Integer> {

    List<Object[]>getUserByName(String name);


}
