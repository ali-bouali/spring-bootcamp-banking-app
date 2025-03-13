package com.alibou.banking.contact;

import java.util.List;

public interface ContactService {

    void addContact(ContactRequest contactRequest, Long userId);
    void updateContact(ContactRequest contactRequest, Long contactId, Long userId);

    void deleteContact(Long contactId);
    List<ContactResponse> findAllContacts(Long userId, int page, int size);
    ContactResponse findById(Long contactId);

    boolean accountExists(String destinationIban, Long userId);
}
