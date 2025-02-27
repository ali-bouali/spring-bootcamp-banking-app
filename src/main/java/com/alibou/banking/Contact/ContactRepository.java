package com.alibou.banking.Contact;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT c from Contact c WHERE c.user.id= :userId AND c.iban= :iban")
    boolean existsByIbanAndUserId(String iban, Long userId);

    List<Contact> findAllByUserId(Long userId, Pageable pageable);
}
