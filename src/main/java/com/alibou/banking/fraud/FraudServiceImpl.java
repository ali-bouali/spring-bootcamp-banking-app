package com.alibou.banking.fraud;

import com.alibou.banking.transaction.Transaction;
import com.alibou.banking.transaction.TransactionStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FraudServiceImpl implements FraudService{
    private final FraudRepository fraudRepository;
    private final FraudMapper fraudMapper;
    @Override
    public List<FraudResponse> getAllFraudsByStatus(int page,int size,String status) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return fraudRepository.findAllByStatus(pageRequest,status).getContent().stream().map(fraudMapper::convertToResponseDTO).toList();
    }

    @Override
    public void validateFraud(Long fraudId,FraudRequest fraudRequest) {
        Fraud existingFraud=fraudRepository.findById(fraudId).orElseThrow(()->new EntityNotFoundException("Fraud don't exist"));

        //change the fraud status
        fraudMapper.merge(fraudRequest,existingFraud);
        fraudRepository.save(existingFraud);

        Transaction transaction=existingFraud.getTransaction();
        //Transaction transaction=transactionRepository.findById(existingFraud.getTransaction().getId())
        if(fraudRequest.getStatus().equals(FraudStatus.CONFIRMED)){
            transaction.setStatus(TransactionStatus.CANCELLED);

        }
        else{
            transaction.setStatus(TransactionStatus.COMPLETED);
        }






    }
}
