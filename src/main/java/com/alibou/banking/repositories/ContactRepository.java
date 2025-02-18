package com.alibou.banking.repositories;

import com.alibou.banking.entites.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByIban(String iban);
}
