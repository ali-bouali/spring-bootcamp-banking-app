package com.alibou.banking.Contact;

import java.util.List;

public interface ContactService {
    void addContact(ContactRequest contactRequest,Long userId);
    void updateContact(ContactRequest contactRequest,Long userId,Long contactId);
    void deleteContact(Long contactId);
    List<ContactResponse> findAllContacts(Long user_id, int page , int size);


}
