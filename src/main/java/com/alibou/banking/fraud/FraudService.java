package com.alibou.banking.fraud;


import java.util.List;

public interface FraudService {
    List<FraudResponse> getAllFraudsByStatus(int page,int size,String status);
    void validateFraud(Long fraudId,FraudRequest fraudRequest);

}
