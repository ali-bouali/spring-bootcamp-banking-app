package com.alibou.banking.contact;

import java.util.List;

public interface ContactService {

    void addContact(ContactRequest contactRequest, Long userId);
    void updateContact(ContactRequest contactRequest, Long contactId, Long userId);

    void deleteContact(Long userId);
    List<ContactResponse> findAllContacts(Long userId, int page, int size);
    ContactResponse findById(Long contactId);


}
