package com.alibou.banking.fraud;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudRequest {
    @Enumerated(EnumType.STRING)
    private FraudType type;
    @Enumerated(EnumType.STRING)
    private FraudStatus status;



}
