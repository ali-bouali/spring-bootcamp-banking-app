package com.alibou.banking.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<user,Integer> {

    Optional<user> findByFirstName(String firstName);



}
