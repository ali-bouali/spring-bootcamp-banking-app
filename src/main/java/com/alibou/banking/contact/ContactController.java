package com.alibou.banking.contact;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@Valid @RequestBody ContactRequest contact, Long userId) {
        contactService.addContact(contact, userId);
    }

    @PutMapping("/{contact-id}")
    public void updateContact(
            @Valid @RequestBody ContactRequest contact,
            @PathVariable("contact-id") Long contactId,
            Long userId
    ) {
        contactService.updateContact(contact, contactId, userId);
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactResponse> findContactById(@PathVariable("contact-id") Long contactId) {
        return ResponseEntity.ok(contactService.findById(contactId));
    }

    @GetMapping("/{contact-name}") // ==> ambiguity
    public ResponseEntity<ContactResponse> findContactByFirstName(
            @PathVariable("contact-name") String contactId
    ) {
        Map<String, String> x = new HashMap<>();
        x.put("contact-id", "wsss");
        x.put("contact-id", "contactId");

        x.get("contact-id");
        return ResponseEntity.ok(null);
    }
}
