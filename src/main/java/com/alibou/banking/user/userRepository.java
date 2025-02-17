package com.alibou.banking.user;

import com.alibou.banking.role.role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userRepository extends JpaRepository<user, Long> {

    List<user> findByFirstName(String firstName);

    List<user> findByLastName(String lastName);

    List<user> findByRole(role role);

    List<user> findByActiveTrue();

    boolean existsByEmail(String email);

    long countByRole(role role);

}

