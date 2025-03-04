package com.alibou.banking.fraud;


import org.springframework.stereotype.Service;

@Service

public class FraudMapper {
    public void merge(FraudRequest fraudRequest, Fraud existingFraud) {
        existingFraud.setType(fraudRequest.getType());
        existingFraud.setStatus(fraudRequest.getStatus());

    }

    public FraudResponse convertToResponseDTO(Fraud fraud) {
        return FraudResponse.builder()
                .id(fraud.getId())
                .date(fraud.getDate())
                .type(fraud.getType())
                .status(fraud.getStatus())
                .build();



    }
}
