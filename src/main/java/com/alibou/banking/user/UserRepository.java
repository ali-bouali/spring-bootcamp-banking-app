package com.alibou.banking.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserById(long id);
    List<User> findUserByFirstName(String firstName);
    List<User> findAllByLastName(String lastName);

    List<User> findAllByRole_Id(long roleId);
    Optional<User> findUserByAccountId(long accountId);

    List<User>findAllByActive(boolean active);
    List<User>findAllByFirstNameContainingIgnoreCase(String firstName);
    int countByFirstName(String firstName);
    long countUsersByRoleId(long roleId);
    long countUserByRoleId(long roleId);
    //etheya mr il far9 binetha

    long countAllByAccountId(long accountId);
    void deleteUserById(long id);
    void deleteAllByActive(boolean active);
    void deleteAllByRoleId(long roleId);
    void deleteAllByAccountId(long accountId);
    boolean existsByEmail(String email);




}
