package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public void deposit(@RequestParam Long userId, @Valid @RequestBody TransactionDepositRequest request) {
        transactionService.deposit(userId, request);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void withdraw(@RequestParam Long userId, @Valid @RequestBody TransactionWithdrawalRequest request) {
        transactionService.withdraw(userId, request);
    }
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void  transfer(@RequestParam Long userId, @Valid @RequestBody TransactionTransferRequest request) {
        transactionService.transfer(userId, request);
    }

    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> findAllTransactionsByUser( Long userId,
                                                                               @RequestParam( value = "page", defaultValue = "0") int page,
                                                                               @RequestParam(value = "size" ,defaultValue = "10") int size) {
        List<TransactionResponse> transactions = transactionService.finaAllTransactions(userId, page, size);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/fraud")
    public ResponseEntity<List<TransactionWithFraudResponse>> findAllTransactionsWithFraud(@RequestParam( value = "page", defaultValue = "0") int page,
                                                                                           @RequestParam( value = "size" ,defaultValue = "10") int size,
                                                                                           @RequestParam FraudType type) {
        List<TransactionWithFraudResponse> fraudTransactions = transactionService.findAllTransactionsWithFraud(page, size, type);
        return ResponseEntity.ok(fraudTransactions);
    }

    @PatchMapping("/{transactionId}/fraud-status")
    public ResponseEntity<String> changeTransactionFraudStatus( @NotNull @PathVariable Long transactionId,
           @NotNull  @RequestParam  FraudStatus fraudStatus) {
        transactionService.changeTransactionFraudStatus(transactionId, fraudStatus);
        return ResponseEntity.ok("Fraud status updated successfully.");
    }
}
