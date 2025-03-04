package com.alibou.banking.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.alibou.banking.contact.Contact;
public interface ContactRepository extends JpaRepository<com.alibou.banking.contact.Contact, Long> {
    @Query("""
               SELECT c FROM Contact c
               WHERE c.iban = :iban
               AND c.user.id = :userId
            """)
    boolean existsByIbanAndUserId(String iban, Long userId);

    Page<com.alibou.banking.contact.Contact> findAllByUserId(Long userId, Pageable pageable);
}
