package com.alibou.banking.fraud;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QPageRequest;

import java.util.List;

public interface FraudRepository extends JpaRepository<Fraud, Long> {
    @Query("SELECT f FROM Fraud f WHERE f.status = :status ")
    List<Fraud> findAllByStatus(PageRequest pageRequest, String status);
}
