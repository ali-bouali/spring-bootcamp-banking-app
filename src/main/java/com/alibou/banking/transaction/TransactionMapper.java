package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import com.alibou.banking.user.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionMapper {

    Transaction mapToTransactionEntity(TransactionDepositRequest request,Long userId){
        return Transaction.builder()
                .description("Deposing money")
                .date(LocalDateTime.now())
                .amount(request.getDepositAmount())
                .type(TransactionType.DEPOSIT)
                .status(TransactionStatus.COMPLETED)
                .user(User.builder().id(userId).build())
                .build();


    }
    public Transaction mapToTransactionEntity(TransactionWithdrawlRequest request, Long userId) {
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
    public Transaction mapToTransactionEntity(TransactionTransferRequest request, Long userId) {
        return Transaction.builder()
                .date(LocalDateTime.now())
                .description(request.getReason())
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
        boolean hasFraud=transaction.getFraud()!=null;
        FraudStatus status=null;
        FraudType type=null;
        if(hasFraud){
            status=transaction.getFraud().getStatus();
            type=transaction.getFraud().getType();

        }
        return TransactionResponse.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .type(transaction.getType())
                .date(transaction.getDate())
                .destinationIbn(transaction.getDestinationIban())
                .hasFraud(hasFraud)
                .frauStatus(status)
                .fraudType(type)
                .build();
    }


}
