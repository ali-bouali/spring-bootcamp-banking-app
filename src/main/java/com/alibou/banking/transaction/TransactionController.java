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
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposit/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deposit(@PathVariable Long userId, @Valid @RequestBody TransactionDepositRequest request) {
        transactionService.deposit(userId, request);
    }

    @PostMapping("/withdraw/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@PathVariable Long userId, @Valid @RequestBody TransactionWithdrawalRequest request) {
        transactionService.withdraw(userId, request);
    }

    @PostMapping("/transfer/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void transfer(@PathVariable Long userId, @Valid @RequestBody TransactionTransferRequest request) {
        transactionService.transfer(userId, request);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<TransactionResponse>> findAllTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.finaAllTransactions(userId, 0, 10));
    }

    @GetMapping("/fraud")
    public ResponseEntity<List<TransactionWithFraudResponse>> findAllTransactionsWithFraud(@RequestParam FraudType type) {
        return ResponseEntity.ok(transactionService.findAllTransactionsWithFraud(0, 10, type));
    }

    @PutMapping("/fraud/{transactionId}")
    @ResponseStatus(HttpStatus.OK)
    public void changeTransactionFraudStatus(FraudStatus fraudStatus, @PathVariable Long transactionId) {
        transactionService.changeTransactionFraudStatus(transactionId, fraudStatus);
    }
}
