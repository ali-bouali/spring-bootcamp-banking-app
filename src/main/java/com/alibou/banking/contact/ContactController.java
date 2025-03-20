package com.alibou.banking.contact;

import com.alibou.banking.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(
            @Valid @RequestBody ContactRequest contact,
            Authentication connectedUser
    ) {
        final long userId = ((User)connectedUser.getPrincipal()).getId();
        contactService.addContact(contact, userId);
    }

    @PutMapping("/{contact-id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(
            @Valid @RequestBody ContactRequest contact,
            @PathVariable("contact-id") Long contactId,
            Authentication connectedUser
    ) {
        final long userId = ((User)connectedUser.getPrincipal()).getId();
        contactService.updateContact(contact, contactId, userId);
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactResponse> findContactById(
            @PathVariable("contact-id") Long contactId
    ) {
        return ResponseEntity.ok(contactService.findById(contactId));
    }
}
