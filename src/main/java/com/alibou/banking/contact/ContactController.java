package com.alibou.banking.contact;

import com.alibou.banking.account.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    @GetMapping
    public ResponseEntity<List<ContactResponse>> findAllAccounts(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            Long userId
    ) {
        return ResponseEntity.ok(contactService.findAllContacts(userId,page, size));
    }
    @DeleteMapping
    public void deleteContactById(Long userId) {
        contactService.deleteContact(userId);
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
