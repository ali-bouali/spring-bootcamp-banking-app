package com.alibou.banking.contact;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository  extends JpaRepository<contact,Integer> {

    Optional<contact> findByFirstName(String firstname);
    Optional<contact> findByIban(String iban);
}
