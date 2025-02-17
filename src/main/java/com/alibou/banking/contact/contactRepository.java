package com.alibou.banking.contact;

import com.alibou.banking.user.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface contactRepository extends JpaRepository<contact, Long> {

    List<contact> findByFirstName(String firstName);

    List<contact> findByLastName(String lastName);

    List<contact> findByUser(user user);

    List<contact> findByFirstNameAndLastName(String firstName, String lastName);

    boolean existsByIban(String iban);

}
