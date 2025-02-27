package com.alibou.banking.Contact;

import com.alibou.banking.user.User;
import com.alibou.banking.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService{
    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;
    private final UserRepository userRepository;


    @Override
    public void addContact(ContactRequest contactRequest, Long userId) {
        if(contactRepository.existsByIbanAndUserId(contactRequest.getIban(),userId)){
            throw new RuntimeException("Contact already exists");
        }
        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User with id:"+userId+"does not exist"));

        Contact contact =contactMapper.mapToContact(contactRequest);
        contact.setUser(user);
        contactRepository.save(contact);
    }


    @Override
    public void updateContact(ContactRequest contactRequest, Long userId, Long contactId) {

        User user=userRepository.findById(userId).orElseThrow(()->new EntityNotFoundException("User with id:"+userId+"does not exist"));
        Contact existingContact =contactRepository.findById(contactId).orElseThrow(()->new EntityNotFoundException("contact with id:"+contactId+"does not exist"));


        boolean sameIban=existingContact.getIban().equals(contactRequest.getIban());
        if(!sameIban && contactRepository.existsByIbanAndUserId(contactRequest.getIban(),userId)){
            throw new RuntimeException("Contact already exists");

        }
        contactMapper.mergeContact(existingContact,contactRequest);
        contactRepository.save(existingContact);

    }

    @Override
    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }

    @Override
    public List<ContactResponse> findAllContacts(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("firstName","lastName"));

        return contactRepository.findAllByUserId(userId,pageable).stream().map(contactMapper::toContactResponse).collect(Collectors.toList());
    }



}
