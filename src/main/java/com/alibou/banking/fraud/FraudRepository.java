package com.alibou.banking.fraud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository extends JpaRepository<Fraud, Long> {
}
