package com.alibou.banking.entities.contact;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    Optional<ContactEntity> findByFirstNameAndLastName(String fn, String ln);

    @Transactional
    @Modifying
    @Query("UPDATE ContactEntity c SET c.lastName = :newLastName WHERE c.lastName = :oldLastName")
    int updateLastName(String oldLastName,String newLastName);

}
