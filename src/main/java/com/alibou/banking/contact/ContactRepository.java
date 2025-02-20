package com.alibou.banking.contact;

import com.alibou.banking.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByIban(String iban);
    List<Contact>findContactByUser(User user);


}
