package com.alibou.banking.contact;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("""
               SELECT COUNT(c) > 0
               FROM Contact c
               WHERE c.iban = :iban
               AND c.user.id = :userId
            """)
    boolean existsByIbanAndUserId(String iban, Long userId);

    Page<Contact> findAllByUserId(Long userId, Pageable pageable);
}
