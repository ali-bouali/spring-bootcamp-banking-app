package com.alibou.banking.contact;

import com.alibou.banking.account.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
@RequiredArgsConstructor
public class contactController {
    private final ContactService contactService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addContact(@Valid @RequestBody ContactRequest contactRequest, Long userId) {
        contactService.addContact(contactRequest, userId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateContact(@Valid @RequestBody ContactRequest contactRequest, Long contactId, Long userId) {
        contactService.updateContact(contactRequest, contactId, userId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteContact(Long userId) {
        contactService.deleteContact(userId);

    }

    @GetMapping
    public ResponseEntity<List<ContactResponse>> findAllContacts(Long userId) {
        return ResponseEntity.ok(contactService.findAllContacts(userId, 1, 20));
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactResponse> findById(
            @PathVariable("contact-id") Long contactId
    ) {
        return ResponseEntity.ok(contactService.findById(contactId));
    }
}
