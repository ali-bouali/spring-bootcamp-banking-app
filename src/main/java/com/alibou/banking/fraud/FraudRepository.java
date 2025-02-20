package com.alibou.banking.fraud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FraudRepository extends JpaRepository<Fraud, Long> {

    @Query("""
            SELECT f from Fraud f 
            """)
    List<Fraud>findAll();
}
