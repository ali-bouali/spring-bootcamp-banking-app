package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import com.alibou.banking.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public void deposit(
            @Valid @RequestBody TransactionDepositRequest request,
            Authentication connectedUser
    ) {
        final long userId = ((User)connectedUser.getPrincipal()).getId();
        transactionService.deposit(userId, request);
    }

    @PostMapping("/withdraw")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void withdraw(
            @Valid @RequestBody TransactionWithdrawalRequest request,
            Authentication connectedUser
    ) {
        final long userId = ((User)connectedUser.getPrincipal()).getId();
        transactionService.withdraw(userId, request);
    }

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void transfer(
            @Valid @RequestBody TransactionTransferRequest request,
            Authentication connectedUser
    ) {
        final long userId = ((User)connectedUser.getPrincipal()).getId();
        transactionService.transfer(userId, request);
    }

    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> findAllTransactionsByUser(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Authentication connectedUser
    ) {
        final long userId = ((User)connectedUser.getPrincipal()).getId();
        List<TransactionResponse> transactions = transactionService.finaAllTransactions(userId, page, size);
        return ResponseEntity.ok(transactions);

    }

    @GetMapping("/fraud")
    // Method Security
    @PreAuthorize("hasRole('ADMIN')") // --> without ROLE_ because it is the default prefix in Spring
    public ResponseEntity<List<TransactionWithFraudResponse>> findAllTransactionsWithFraud(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam FraudType type
    ) {
        List<TransactionWithFraudResponse> fraudTransactions = transactionService.findAllTransactionsWithFraud(page, size, type);
        return ResponseEntity.ok(fraudTransactions);
    }

    @PatchMapping("/{transaction-id}/fraud-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> changeTransactionFraudStatus(
            @PathVariable("transaction-id") Long transactionId,
            @RequestParam FraudStatus fraudStatus) {
        transactionService.changeTransactionFraudStatus(transactionId, fraudStatus);
        return ResponseEntity.ok("Fraud status updated successfully.");
    }
}
