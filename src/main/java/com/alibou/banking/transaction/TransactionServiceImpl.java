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
    public void withdraw(CreateTransactionRequest transactionRequest, Long userId) {
        BigDecimal balance = getBalance(userId);
        BigDecimal amount = transactionRequest.getTransactionRequest().getAmount();
        if (balance.compareTo(amount) < 0)
            saveTransaction(transactionRequest, TransactionType.WITHDRAWAL, TransactionStatus.FAILED);

        else
            saveTransaction(transactionRequest, TransactionType.WITHDRAWAL, TransactionStatus.COMPLETED);

    }


    @Override
    public void deposit(CreateTransactionRequest transactionRequest, Long userId) {
        String sourceIban = transactionRequest.getTransactionRequest().getSourceIban();
        BigDecimal amount = transactionRequest.getTransactionRequest().getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit must be positive and not null");
        }
        saveTransaction(transactionRequest, TransactionType.WITHDRAWAL, TransactionStatus.COMPLETED);


    }

    @Override
    public void transfer(CreateTransactionRequest transactionRequest, Long userId) {
        BigDecimal balance = getBalance(userId);
        BigDecimal amount = transactionRequest.getTransactionRequest().getAmount();
        String sourceIban = transactionRequest.getTransactionRequest().getSourceIban();
        String destinationIban = transactionRequest.getTransactionRequest().getDestinationIban();
        if (destinationIban == null || destinationIban.isEmpty()) {
            throw new RuntimeException("Destination IBAN is empty");
        }
        if (sourceIban.equals(destinationIban)) {
            throw new RuntimeException("you cannot send money to yourself");
        }
        Boolean existContact = contactRepository.existsByIbanAndUserId(destinationIban, userId);
        if (!existContact) {
            contactService.addContact(transactionRequest.getContactRequest(), userId);

        }
        if (balance.compareTo(amount) < 0)
            saveTransaction(transactionRequest, TransactionType.WITHDRAWAL, TransactionStatus.FAILED);

        else{

            saveTransaction(transactionRequest, TransactionType.WITHDRAWAL, TransactionStatus.COMPLETED);
        }
    }

    @Override
    public void validateTransaction(TransactionRequest transactionRequest) {


    }

    @Override
    public List<TransactionResponse> findAllTransaction(Long userId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("date"));
        return transactionRepository.findAllByUserId(userId, pageable)
                .getContent()
                .stream()
                .map(transactionMapper::toTansactionResponse)
                .toList();
    }

    @Override
    public TransactionResponse findById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .map(transactionMapper::toTansactionResponse)
                .orElseThrow(() -> new EntityNotFoundException("Transaction with id " + transactionId + "not found"));
    }

    private void saveTransaction(CreateTransactionRequest transactionRequest, TransactionType type, TransactionStatus status) {

        Transaction currentTransaction = transactionMapper.toTransactionEntity(transactionRequest.getTransactionRequest());
        currentTransaction.setType(type);
        currentTransaction.setStatus(status);
        currentTransaction.setDate(LocalDateTime.now());
        transactionRepository.save(currentTransaction);
    }

    private BigDecimal getBalance(Long userId) {

        BigDecimal totalDeposit = transactionRepository.getTransactionsByType("DEPOSIT", userId);
        BigDecimal totalWithdraw = transactionRepository.getTransactionsByType("WITHDRAWAL", userId);
        BigDecimal currentBalance = totalDeposit.subtract(totalWithdraw);
        return currentBalance;
    }
}
