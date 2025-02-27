package com.alibou.banking.account;

public interface AccountService {

    void createAccount(CreateAccountRequest accountRequest);

    void lockAccount(Long accountId);
    void unlockAccount(Long accountId);

}
