package com.alibou.banking.fraud;


import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudResponse {

    private FraudType type;
    private FraudStatus status;
    private LocalDateTime date;
}
