package com.alibou.banking.Contact;

import java.util.List;
import com.alibou.banking.contact.ContactRequest;
import  com.alibou.banking.contact.ContactResponse;

public interface ContactService {

    void addContact(ContactRequest contactRequest, Long userId);
    void updateContact(ContactRequest contactRequest, Long contactId, Long userId);

    void deleteContact(Long userId);
    List<ContactResponse> findAllContacts(Long userId, int page, int size);
    ContactResponse findById(Long contactId);
    boolean accountExists(String destinationIban, Long userId);

}
