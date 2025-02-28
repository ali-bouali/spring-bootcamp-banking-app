package com.alibou.banking.contact;

import org.springframework.stereotype.Service;
import com.alibou.banking.contact.ContactRequest;
import com.alibou.banking.contact.Contact;
@Service
public class ContactMapper {
    public Contact toContactEntity( ContactRequest contactRequest) {
        return Contact.builder()
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .iban(contactRequest.getIban())
                .build();
    }

    public void mergeContact(Contact existingContact, ContactRequest contactRequest) {
        existingContact.setFirstName(contactRequest.getFirstName());
        existingContact.setLastName(contactRequest.getLastName());
        existingContact.setIban(contactRequest.getIban());
    }

    public com.alibou.banking.contact.ContactResponse toContactResponse(Contact c) {
        return null;
    }
}
