package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public void deposit(
            @RequestBody @Valid TransactionDepositRequest transactionDepositRequest,
            Long userId
    ) {
        transactionService.deposit(userId, transactionDepositRequest);
    }
    @PostMapping("/withdrawl")
    @ResponseStatus(HttpStatus.CREATED)
    public void withdrawl(
            @RequestBody @Valid TransactionWithdrawalRequest transactionWithdrawalRequest,
            Long userId
    ) {
        transactionService.withdraw(userId, transactionWithdrawalRequest);
    }
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public void transfer(
            @Valid @RequestBody TransactionTransferRequest transactionTransferRequest,
            Long userId
    ){
        transactionService.transfer(userId, transactionTransferRequest);
    }
    @GetMapping
    public ResponseEntity<List<TransactionResponse>>findAllTransactions(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            Long userId


    )
    {
        return ResponseEntity.ok(transactionService.finaAllTransactions(userId, page, size));
    }
    @GetMapping("/fraud")
    public ResponseEntity<List<TransactionWithFraudResponse>> findAllTransactionsWithFraud(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam FraudType type
            ){
        return ResponseEntity.ok(transactionService.findAllTransactionsWithFraud(page, size, type));
    }
    @PatchMapping("/{transaction-id}/fraud-status")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeTransactionFraudStatus(
            @Valid @PathVariable("transaction-id")Long transctionId,
            @RequestParam FraudStatus newStatus

            ){
        transactionService.changeTransactionFraudStatus(transctionId, newStatus);

    }

}
