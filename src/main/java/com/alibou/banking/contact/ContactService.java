package com.alibou.banking.contact;

import com.alibou.banking.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact findByIban(String iban) {
        Optional<Contact> contact = contactRepository.findByIban(iban);
        return contact.orElse(null);
    }

    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void removeContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public List<Contact> findByUser(User user) {
        return contactRepository.findContactByUser(user);
    }
}