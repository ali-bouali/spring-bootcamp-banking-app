package com.alibou.banking.transaction;


import com.alibou.banking.contact.ContactRepository;
import com.alibou.banking.contact.ContactService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final ContactRepository contactRepository;
    private final ContactService contactService;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public void withdraw(CreateTransactionRequest transactionRequest,Long userId) {
        BigDecimal balance=getBalance(userId);
        BigDecimal amount=transactionRequest.getTransactionRequest().getAmount();
        String sourceIban=transactionRequest.getTransactionRequest().getSourceIban();




    }

    @Override
    public void deposit(CreateTransactionRequest transactionRequest,Long userId) {
        BigDecimal balance=getBalance(userId);



    }

    @Override
    public void transfer(CreateTransactionRequest transactionRequest,Long userId) {
        BigDecimal balance=getBalance(userId);
        BigDecimal amount=transactionRequest.getTransactionRequest().getAmount();
        String sourceIban=transactionRequest.getTransactionRequest().getSourceIban();
        String destinationIban=transactionRequest.getTransactionRequest().getDestinationIban();
        if(destinationIban==null || destinationIban.isEmpty()){
            throw new RuntimeException("Destination IBAN is empty");
        }
        if(sourceIban.equals(destinationIban)){
            throw new RuntimeException("you cannot send money to yourself");
        }
        Boolean existContact =contactRepository.existsByIbanAndUserId(destinationIban,userId);
        if(!existContact){
            contactService.addContact(transactionRequest.getContactRequest(),userId);

        }
        if(balance.compareTo(amount)<=0){
            Transaction currentTransaction=transactionMapper.toTransactionEntity(transactionRequest.getTransactionRequest());
            currentTransaction.setType(TransactionType.WITHDRAWAL);
            currentTransaction.setStatus(TransactionStatus.FAILED);
            currentTransaction.setDate(LocalDateTime.now());
            transactionRepository.save(currentTransaction);

            throw new RuntimeException("you don't have enough money");

        }
        //benesba lel fraud Pratiquement nfs 5edma just a3ml verification 3al amount (ya akther men 5 mil wela akther men 40%)
        Transaction currentTransaction=transactionMapper.toTransactionEntity(transactionRequest.getTransactionRequest());
        currentTransaction.setType(TransactionType.WITHDRAWAL);
        currentTransaction.setStatus(TransactionStatus.COMPLETED);
        currentTransaction.setDate(LocalDateTime.now());
        transactionRepository.save(currentTransaction);
    }

    @Override
    public void validateTransaction(TransactionRequest transactionRequest) {

    }

    @Override
    public List<TransactionResponse> findAllTransaction(Long userId, int page, int size) {

        Pageable pageable = PageRequest.of(page,size, Sort.by("date"));
        return transactionRepository.findAllByUserId(userId ,pageable)
                .getContent()
                .stream()
                .map(transactionMapper::toTansactionResponse)
                .toList();


    }

    @Override
    public TransactionResponse findById(Long transactionId) {
       return transactionRepository.findById(transactionId)
               .map(transactionMapper::toTansactionResponse)
               .orElseThrow(()-> new EntityNotFoundException("Transaction with id " + transactionId + "not found"));
    }

    private BigDecimal getBalance(Long userId){

        BigDecimal totalDeposit = transactionRepository.getTransactionsByType("DEPOSIT",userId);
        BigDecimal totalWithdraw = transactionRepository.getTransactionsByType("WITHDRAWAL",userId);
        BigDecimal currentBalance=totalDeposit.subtract(totalWithdraw);
        return currentBalance;
    }
}
