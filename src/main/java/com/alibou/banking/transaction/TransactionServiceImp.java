package com.alibou.banking.transaction;

import com.alibou.banking.Contact.ContactService;
import com.alibou.banking.fraud.Fraud;
import com.alibou.banking.fraud.FraudRepository;
import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final ContactService contactService;
    private final FraudRepository fraudRepository;
    @Override
    public void deposit(Long userId, TransactionDepositRequest request) {
        Transaction transaction = transactionMapper.mapToTransactionEntity(request,userId);
        transactionRepository.save(transaction);
    }

    @Override
    public void withdraw(Long userId, TransactionWithdrawlRequest request) {

        BigDecimal balance=transactionRepository.calculateAccountBanlance(userId);
        if(balance.compareTo(request.getWithdrawalAmount())<0){
            throw new RuntimeException("Insufficient balance");
        }
        Transaction transaction = transactionMapper.mapToTransactionEntity(request,userId);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public void transfer(Long userId, TransactionTransferRequest request) {
        String ressourceIban =request.getSourceIban();
        String destIban =request.getDestinationIban();
        if(ressourceIban.equals(destIban)){
            throw new RuntimeException ("You can not send money to Yourself");
        }
        BigDecimal balance=transactionRepository.calculateAccountBanlance(userId);
        if(balance.compareTo(request.getTransferAmount())<0){
            throw new RuntimeException("Insufficient balance");
        }

        if(!contactService.accountExists(destIban,userId)){
                contactService.addContact(request.getContactRequest(),userId);
        }

        Transaction transaction = transactionMapper.mapToTransactionEntity(request,userId);
        boolean fraudHasBeenDetected=isFraudTranfer(balance,request.getTransferAmount());

        if(fraudHasBeenDetected){
            transaction.setStatus(TransactionStatus.PENDING);
        }
        Transaction savedTransaction=transactionRepository.save(transaction);

        if(fraudHasBeenDetected){
            Fraud fraud=Fraud.builder()
                    .transaction(savedTransaction)
                    .date(LocalDateTime.now())
                    .type(FraudType.MONEY_LAUNDERING)
                    .status(FraudStatus.UNDER_INVESTIGATION)
                    .build();
            fraudRepository.save(fraud);

        }


    }

    private boolean isFraudTranfer(BigDecimal balance, BigDecimal transferAmount) {
        boolean isGreaterThan5000 =transferAmount.compareTo(BigDecimal.valueOf(5000)) >0;
        BigDecimal accountBalance40Percent=balance.multiply(BigDecimal.valueOf(0.4));
        return isGreaterThan5000 ||transferAmount.compareTo(accountBalance40Percent)>0;

    }

    @Override
    @Transactional
    public List<TransactionResponse> findAllTransaction(Long userId, int page, int size) {
        PageRequest pageRequest= PageRequest.of(page,size);

        return transactionRepository.findAllByUserId(userId,pageRequest)
                .stream()
                .map(transactionMapper::toTransactionResponse)
                .collect(Collectors.toList());
    }
}
