package com.alibou.banking.transaction;

import com.alibou.banking.contact.ContactService;
import com.alibou.banking.exceptions.TransactionException;
import com.alibou.banking.fraud.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock private TransactionRepository transactionRepository;
    @Mock private TransactionMapper transactionMapper;
    @Mock private FraudRepository fraudRepository;
    @Mock private ContactService contactService;

    @InjectMocks private TransactionServiceImpl transactionService;

    private Transaction transaction;
    private TransactionDepositRequest depositRequest;
    private TransactionWithdrawalRequest withdrawalRequest;
    private TransactionTransferRequest transferRequest;
    private Fraud fraud;

    @BeforeEach
    void setUp() {
        transaction = createTransaction();
        depositRequest = new TransactionDepositRequest();
        withdrawalRequest = new TransactionWithdrawalRequest(BigDecimal.valueOf(500));
        transferRequest = new TransactionTransferRequest("Rent",BigDecimal.valueOf(600),"IBAN1", "IBAN2",  null);
        fraud = createFraud(transaction);
    }

    private Transaction createTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setAmount(BigDecimal.valueOf(1000));
        return transaction;
    }

    private Fraud createFraud(Transaction transaction) {
        return Fraud.builder()
                .transaction(transaction)
                .date(LocalDateTime.now())
                .type(FraudType.MONEY_LAUNDERING)
                .status(FraudStatus.UNDER_INVESTIGATION)
                .build();
    }

    @Test
    void deposit_ShouldSaveTransaction() {
        when(transactionMapper.toTransactionEntity(depositRequest, 1L)).thenReturn(transaction);

        transactionService.deposit(1L, depositRequest);

        verify(transactionRepository).save(transaction);
    }

    @Test
    void withdraw_ShouldThrowException_WhenInsufficientFunds() {
        when(transactionRepository.calculateAccountBalance(1L)).thenReturn(BigDecimal.valueOf(400));

        assertThrows(TransactionException.class, () ->
                transactionService.withdraw(1L, withdrawalRequest)
        );
    }

    @Test
    void withdraw_ShouldSaveTransaction_WhenSufficientFunds() {
        when(transactionRepository.calculateAccountBalance(1L)).thenReturn(BigDecimal.valueOf(1000));
        when(transactionMapper.toTransactionEntity(withdrawalRequest, 1L)).thenReturn(transaction);

        transactionService.withdraw(1L, withdrawalRequest);

        verify(transactionRepository).save(transaction);
    }

    @Test
    void transfer_ShouldThrowException_WhenSendingToSelf() {
        transferRequest.setDestinationIban("IBAN1");
        assertThrows(TransactionException.class, () ->
                transactionService.transfer(1L, transferRequest)
        );
    }

    @Test
    void transfer_ShouldThrowException_WhenInsufficientFunds() {
        when(transactionRepository.calculateAccountBalance(1L)).thenReturn(BigDecimal.valueOf(500));
        assertThrows(TransactionException.class, () ->
                transactionService.transfer(1L, transferRequest)
        );
    }

    @Test
    void transfer_ShouldCreateNewContact_WhenDestinationNotExists() {
        when(transactionRepository.calculateAccountBalance(1L)).thenReturn(BigDecimal.valueOf(2000));
        when(contactService.accountExists("IBAN2", 1L)).thenReturn(false);
        when(transactionMapper.toTransactionEntity(transferRequest, 1L)).thenReturn(transaction);

        transactionService.transfer(1L, transferRequest);

        verify(contactService).addContact(transferRequest.getContact(), 1L);
        verify(transactionRepository).save(transaction);
    }

    @Test
    void transfer_ShouldNotCreateNewContact_WhenDestinationExists() {
        when(transactionRepository.calculateAccountBalance(1L)).thenReturn(BigDecimal.valueOf(2000));
        when(contactService.accountExists("IBAN2", 1L)).thenReturn(true);
        when(transactionMapper.toTransactionEntity(transferRequest, 1L)).thenReturn(transaction);

        transactionService.transfer(1L, transferRequest);

        verify(contactService, never()).addContact(any(), anyLong());
        verify(transactionRepository).save(transaction);
    }

    @Test
    void transfer_ShouldDetectFraud_WhenAmountExceedsLimits() {
        when(transactionRepository.calculateAccountBalance(1L)).thenReturn(BigDecimal.valueOf(2000));
        when(transactionMapper.toTransactionEntity(transferRequest, 1L)).thenReturn(transaction);
        when(contactService.accountExists("IBAN2", 1L)).thenReturn(true);

        transactionService.transfer(1L, transferRequest);

        verify(fraudRepository).save(any(Fraud.class));
    }

    @Test
    void findAllTransactions_ShouldReturnTransactionResponses() {
        Page<Transaction> page = new PageImpl<>(List.of(transaction));
        when(transactionRepository.findAllByUserId(1L, PageRequest.of(0, 5))).thenReturn(page);
        when(transactionMapper.toTransactionResponse(any(Transaction.class))).thenReturn(new TransactionResponse());

        List<TransactionResponse> responses = transactionService.finaAllTransactions(1L, 0, 5);

        assertEquals(1, responses.size());
    }

    @Test
    void findAllTransactionsWithFraud_ShouldReturnTransactions() {
        Page<Transaction> page = new PageImpl<>(List.of(transaction));
        when(transactionRepository.findAllTransactionsHavingFraud(FraudType.MONEY_LAUNDERING, PageRequest.of(0, 5))).thenReturn(page);
        when(transactionMapper.toTransactionWithFraudResponse(any(Transaction.class)))
                .thenReturn(new TransactionWithFraudResponse());

        List<TransactionWithFraudResponse> responses = transactionService.findAllTransactionsWithFraud(0, 5, FraudType.MONEY_LAUNDERING);

        assertEquals(1, responses.size());
    }

    @Test
    void findAllByProj_ShouldReturnTransactionProjections() {
        Page<TransactionWithFraudProjection> page = new PageImpl<>(List.of(mock(TransactionWithFraudProjection.class)));
        when(transactionRepository.findAllTransactionsHavingFraudProj(FraudType.PHISHING, PageRequest.of(0, 5))).thenReturn(page);

        List<TransactionWithFraudProjection> projections = transactionService.findAllByProj(0, 5);

        assertEquals(1, projections.size());
    }

    @Test
    void changeTransactionFraudStatus_ShouldThrowException_WhenUnderInvestigation() {
        assertThrows(TransactionException.class, () ->
                transactionService.changeTransactionFraudStatus(1L, FraudStatus.UNDER_INVESTIGATION)
        );
    }

    @Test
    void changeTransactionFraudStatus_ShouldUpdateStatus_WhenFraudExists() {
        transaction.setFraud(fraud);
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        transactionService.changeTransactionFraudStatus(1L, FraudStatus.CONFIRMED);

        assertEquals(TransactionStatus.CANCELLED, transaction.getStatus());
        verify(transactionRepository).save(transaction);
        verify(fraudRepository).save(fraud);
    }

}
