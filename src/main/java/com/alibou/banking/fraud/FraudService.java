package com.alibou.banking.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FraudService {

    private FraudRepository fraudRepository;


    public List<Fraud>getAllFrauds(){
        return fraudRepository.findAdd();
    }
}