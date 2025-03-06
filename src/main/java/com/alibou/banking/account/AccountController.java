package com.alibou.banking.account;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<List<AccountResponse>> findAllAccounts() {
        return ResponseEntity.ok(accountService.findAllAccounts(0, 10));
    }

    @GetMapping("/{account-id}")
    public ResponseEntity<AccountResponse> findAccountById(
            @PathVariable("account-id") Long accountId
    ) {
        return ResponseEntity.ok(accountService.findAccountById(accountId));
    }
}
