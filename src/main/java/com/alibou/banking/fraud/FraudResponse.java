package com.alibou.banking.fraud;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class FraudResponse {
    private Long id;
    private FraudType type;
    private FraudStatus status;
    private LocalDateTime date;
}
