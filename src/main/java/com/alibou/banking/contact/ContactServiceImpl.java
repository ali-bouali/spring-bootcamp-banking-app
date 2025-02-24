package com.alibou.banking.contact;

import com.alibou.banking.user.User;
import com.alibou.banking.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    @Override
    public void createContact(ContactRequest contactRequest) {
        User user = userRepository.findById(contactRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        //Checking if iban exists
        if (contactRepository.existsByUserIdAndIban(contactRequest.getUserId(), contactRequest.getIban())) {
            throw new RuntimeException("Contact already exists");
        }
        //Build the contact
        Contact contact = Contact.builder()
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .iban(contactRequest.getIban())
                .user(user)
                .build();

        contactRepository.save(contact);
    }

    @Override
    public void updateContact(Long contactId, ContactRequest contactRequest, Long userId) {
        Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));

        // checking if the iban exist
        if (contactRepository.existsByUserIdAndIban(contactId, contactRequest.getIban())) {
            throw new RuntimeException("Contact already exists");
        }
        contact.setFirstName(contactRequest.getFirstName());
        contact.setLastName(contactRequest.getLastName());
        contact.setIban(contactRequest.getIban());
        contactRepository.save(contact);
    }

    @Override
    public void activateContact(Long contactId, Long userId) {
        Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        if (contact.getIsActive()) {
            throw new RuntimeException("Contact already activated");
        }
        contact.setIsActive(true);
        contactRepository.save(contact);

    }

    @Override
    public void deactivateContact(Long contactId, Long userId) {
        Contact contact = contactRepository.findByIdAndUserId(contactId, userId)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
        if (!contact.getIsActive()) {
            throw new RuntimeException("Contact already inactive");
        }
        contact.setIsActive(false);
        contactRepository.save(contact);

    }
}
