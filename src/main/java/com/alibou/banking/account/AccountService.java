package com.alibou.banking.account;

public interface AccountService {

    void createAccount(CreateAccountRequest accountRequest);

    void deactivateAccount(Long accountId);

    void activateAccount(Long accountId);

}
