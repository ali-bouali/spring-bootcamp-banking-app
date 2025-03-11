package com.alibou.banking.transaction;

import com.alibou.banking.fraud.FraudStatus;
import com.alibou.banking.fraud.FraudType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/transaction")
public class TransactionController {

    private TransactionService transactionService;
    @PostMapping("/deposit/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void deposit(@PathVariable("userid") Long userId,TransactionDepositRequest request) {
        transactionService.deposit(userId, request);
    }

    @PostMapping("/withdraw/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@PathVariable("userid") Long userId,TransactionWithdrawalRequest request) {
        transactionService.withdraw(userId, request);
    }

    @PostMapping("/transfer/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@PathVariable("userid") Long userId,TransactionTransferRequest request) {
        transactionService.transfer(userId, request);
    }


    @GetMapping("/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TransactionResponse>> getAllTransactions (@PathVariable("userid") Long userId) {
        return ResponseEntity.ok( transactionService.finaAllTransactions(userId,0,10));
    }


    @GetMapping("/fraud")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TransactionWithFraudResponse>>getAllTransactionWithFrauds (  @RequestBody FraudType type) {
        return ResponseEntity.ok(transactionService.findAllTransactionsWithFraud(0,10,type)     );
    }

    @PutMapping("/update/{idt}")
    @ResponseStatus(HttpStatus.OK)
    public void changeTransactionFraudStatus(@PathVariable("idt")Long transactionId, @RequestBody FraudStatus fraudStatus){
        transactionService.changeTransactionFraudStatus(transactionId,fraudStatus);
    }

}
