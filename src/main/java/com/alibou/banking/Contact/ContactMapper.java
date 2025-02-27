package com.alibou.banking.Contact;


import org.springframework.stereotype.Service;

@Service
public class ContactMapper {
    public Contact mapToContact(ContactRequest contactRequest) {
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

    public  ContactResponse toContactResponse(Contact Contact) {
        return ContactResponse.builder()
                .id(Contact.getId())
                .firstName(Contact.getFirstName())
                .lastName(Contact.getLastName())
                .iban(Contact.getIban())
                .build();
    }
}
