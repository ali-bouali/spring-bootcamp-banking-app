package com.alibou.banking.contact;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/contact/")
@RequiredArgsConstructor
public class ContactController {
    private ContactService contactService;



    @PostMapping("/{id-user}")
    @ResponseStatus(HttpStatus.OK)
    public void addContact(@Valid  @RequestBody ContactRequest contact, @PathVariable("id-user") Long userId) {
        contactService.addContact(contact,userId);
    }

    @DeleteMapping("/{contact-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteContact(@PathVariable("contact-id") Long contactId) {
        contactService.deleteContact(contactId);
    }

    @GetMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ContactResponse>> getAllContacts(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok( contactService.findAllContacts(userId,10,10) ) ;
    }


    @GetMapping("/one/{contact-id}")
    public ResponseEntity<ContactResponse> getContactById(@PathVariable("contact-id") Long contactId) {
       return  ResponseEntity.ok( contactService.findById(contactId))  ;
    }


    @GetMapping("/exists/{contact-id}")
    public ResponseEntity<Boolean> accountExists(@PathVariable("contact-id") Long contactId , @RequestBody String destinationIban   ) {
        return ResponseEntity.ok( contactService.accountExists(destinationIban,contactId) );
    }


}