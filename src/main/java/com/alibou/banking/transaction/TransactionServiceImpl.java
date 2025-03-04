package com.alibou.banking.transaction;

import com.alibou.banking.contact.ContactService;
import com.alibou.banking.fraud.Fraud;
import com.alibou.banking.fraud.FraudRepository;
import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final FraudRepository fraudRepository;
    private final ContactService contactService;

    @Override
    public void deposit(Long userId, TransactionDepositRequest request) {

        Transaction transaction = transactionMapper.toTransactionEntity(request, userId);
        transactionRepository.save(transaction);

    }

    @Override
    public void withdraw(Long userId, TransactionWithdrawalRequest request) {

        BigDecimal accountBalance = transactionRepository.calculateAccountBalance(userId);

        Transaction withdrwalTransaction = transactionMapper.toTransactionEntity(request, userId);

        // Early return
        if (accountBalance.compareTo(request.getWithdrawalAmount()) < 0) {
            // todo create a better exception
            throw new RuntimeException("Withdrawal amount exceeds account balance");
        }

        transactionRepository.save(withdrwalTransaction);


    }

    @Override
    @Transactional
    public void transfer(Long userId, TransactionTransferRequest request) {
        String sourceIban = request.getSourceIban();
        String destinationIban = request.getDestinationIban();

        if (sourceIban.equals(destinationIban)) {
            throw new RuntimeException("You cannot send money to yourself");
        }

        BigDecimal accountBalance = transactionRepository.calculateAccountBalance(userId);
        if (accountBalance.compareTo(request.getTransferAmount()) < 0) {
            // todo create a better exception
            throw new RuntimeException("Insufficient funds");
        }

        if (!contactService.accountExists(destinationIban, userId)) {
            contactService.addContact(request.getContact(), userId);
        }

        Transaction transferTransaction = transactionMapper.toTransactionEntity(request, userId);

        // detect fraud
        boolean fraudHasBeenDetected = isFraudTransfer(accountBalance, request.getTransferAmount());
        if (fraudHasBeenDetected) {
            transferTransaction.setStatus(TransactionStatus.PENDING);
        }
        Transaction savedTransaction = transactionRepository.save(transferTransaction);

        if (fraudHasBeenDetected) {
            Fraud fraud = Fraud.builder()
                    .transaction(savedTransaction)
                    .date(LocalDateTime.now())
                    .type(FraudType.MONEY_LAUNDERING)
                    .status(FraudStatus.UNDER_INVESTIGATION)
                    .build();

            fraudRepository.save(fraud);
        }
    }

    @Override
    @Transactional
    public List<TransactionResponse> finaAllTransactions(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return transactionRepository.findAllByUserId(userId, pageRequest)
                .getContent()
                .stream()
                .map(transactionMapper::toTransactionResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<TransactionWithFraudResponse> findAllTransactionsWithFraud(int page, int size, FraudType type) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return transactionRepository.findAllTransactionsHavingFraud(type, pageRequest)
                .getContent()
                .stream()
                .map(transactionMapper::toTransactionWithFraudResponse)
                .toList();
    }

    @Override
    @Transactional
    public void changeTransactionFraudStatus(Long transactionId, FraudStatus fraudStatus) {
        if (FraudStatus.UNDER_INVESTIGATION.equals(fraudStatus)) {
            throw new RuntimeException("Unsupported status");
        }
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        Fraud fraud = transaction.getFraud();

        // check if this is really a fraud
        fraud.setStatus(fraudStatus);
        if (FraudStatus.CONFIRMED.equals(fraudStatus)) {
            transaction.setStatus(TransactionStatus.CANCELLED);
        } else if (FraudStatus.REJECTED.equals(fraudStatus)) {
            transaction.setStatus(TransactionStatus.COMPLETED);
        }
        transactionRepository.save(transaction);
        fraudRepository.save(fraud);

    }

    private boolean isFraudTransfer(BigDecimal accountBalance, BigDecimal transferAmount) {
        boolean isGreaterThat5000 = transferAmount.compareTo(BigDecimal.valueOf(5000)) > 0;
        BigDecimal accountBalance40Percent = accountBalance.multiply(BigDecimal.valueOf(0.4));
        boolean isGreaterThan40Percent = transferAmount.compareTo(accountBalance40Percent) > 0;

        return isGreaterThan40Percent || isGreaterThat5000;
    }
}
