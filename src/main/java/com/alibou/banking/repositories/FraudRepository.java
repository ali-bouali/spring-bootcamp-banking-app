package com.alibou.banking.repositories;

import com.alibou.banking.entites.fraud.Fraud;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository extends JpaRepository<Fraud,Long> {
}
