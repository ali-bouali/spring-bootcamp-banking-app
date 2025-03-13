package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import com.alibou.banking.user.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionMapper {

    public Transaction toTransactionEntity(TransactionDepositRequest request, Long userId) {
        return Transaction.builder()
                .date(LocalDateTime.now())
                .description("Deposing money")
                .amount(request.getDepositAmount())
                .type(TransactionType.DEPOSIT)
                .status(TransactionStatus.COMPLETED)
                .user(
                        User.builder()
                                .id(userId)
                                .build()
                )
                .build();
    }

    public Transaction toTransactionEntity(TransactionWithdrawalRequest request, Long userId) {
        return Transaction.builder()
                .date(LocalDateTime.now())
                .description("Withdrawing money")
                .amount(request.getWithdrawalAmount().multiply(BigDecimal.valueOf(-1)))
                .type(TransactionType.WITHDRAW)
                .status(TransactionStatus.COMPLETED)
                .user(
                        User.builder()
                                .id(userId)
                                .build()
                ).build();
    }

    public Transaction toTransactionEntity(TransactionTransferRequest request, Long userId) {
        return Transaction.builder()
                .date(LocalDateTime.now())
                .description(request.getRaison())
                .amount(request.getTransferAmount().multiply(BigDecimal.valueOf(-1)))
                .type(TransactionType.TRANSFER)
                .status(TransactionStatus.COMPLETED)
                .user(
                        User.builder()
                                .id(userId)
                                .build()
                ).build();
    }

    public TransactionResponse toTransactionResponse(Transaction transaction) {
        boolean hasFraud = transaction.getFraud() != null;
        FraudStatus status = null;
        FraudType type = null;
        if (hasFraud) {
            status = transaction.getFraud().getStatus();
            type = transaction.getFraud().getType();
        }
        return TransactionResponse.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .destinationIbn(transaction.getDestinationIban())
                .type(transaction.getType())
                .status(transaction.getStatus())
                .hasFraud(hasFraud)
                .fraudStatus(status)
                .fraudType(type)
                .build();
    }

    public TransactionWithFraudResponse toTransactionWithFraudResponse(Transaction transaction) {
        return TransactionWithFraudResponse.builder()
                .transactionId(transaction.getId())
                .transactionDescription(transaction.getDescription())
                .userFullName(transaction.getUser().fullName())
                .sourceIban(transaction.getSourceIban())
                .destinationIban(transaction.getDestinationIban())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getDate())
                .transactionStatus(transaction.getStatus())
                .transactionUpdatedAt(transaction.getLastModifiedDate())
                .fraudStatus(transaction.getFraud().getStatus())
                .fraudDate(transaction.getFraud().getDate())
                .fraudUpdatedAt(transaction.getFraud().getLastModifiedDate())
                .build();
    }
}
