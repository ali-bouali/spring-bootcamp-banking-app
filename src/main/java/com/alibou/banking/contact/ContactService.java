package com.alibou.banking.contact;

public interface ContactService {

    void createContact(ContactRequest contactRequest);
    void updateContact(Long contactId, ContactRequest contactRequest, Long userId);
    void activateContact(Long contactId, Long userId);
    void deactivateContact(Long contactId, Long userId);
}
