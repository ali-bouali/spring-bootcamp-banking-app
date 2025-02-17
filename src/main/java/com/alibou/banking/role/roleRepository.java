package com.alibou.banking.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface roleRepository extends JpaRepository<role, Long> {

    boolean existsByName(String name);

    List<role> findByNameContaining(String keyword);
}
