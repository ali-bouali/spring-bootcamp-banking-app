package com.alibou.banking.account;

import java.util.List;

public interface AccountService {

    void createAccount(CreateAccountRequest accountRequest);

    void lockAccount(Long accountId);

    void unlockAccount(Long accountId);
    List<AccountResponse> findAllAccounts(int page, int size);
    AccountResponse findAccountById(Long accountId);

}
