package com.alibou.banking.contact;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByUserIdAndIban(Long userId, String iban);

    Optional<Contact> findByIdAndUserId(Long id, Long userId);
}