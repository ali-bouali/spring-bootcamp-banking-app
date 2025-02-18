package com.alibou.banking.entities.fraud;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository extends JpaRepository<FraudEntity, Long>{
}
