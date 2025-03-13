package com.alibou.banking.account;

import jakarta.validation.Valid;
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
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@Valid @RequestBody CreateAccountRequest accountRequest) {
        accountService.createAccount(accountRequest);
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findAllAccounts(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(accountService.findAllAccounts(page, size));
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountResponse> findAccountById(
            @PathVariable("account-id") Long accountId
    ) {
        return ResponseEntity.ok(accountService.findAccountById(accountId));
    }

    @PatchMapping("/lock")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void lockAccount(Long accountId) {
        accountService.lockAccount(accountId);
    }

    @PatchMapping("/unlock")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void unlockAccount(Long accountId) {
        accountService.unlockAccount(accountId);
    }
}
