package com.alibou.banking.contact;

import com.alibou.banking.account.CreateAccountRequest;

public interface ContactService {

    void createContact(CreateContactRequest contactRequest);
    void updateContact(Long contactId, UpdateContactRequest contactRequest, Long userId);
    void activateContact(Long contactId, Long userId);
    void deactivateContact(Long contactId, Long userId);
}
