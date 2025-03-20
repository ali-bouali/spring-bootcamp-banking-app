package com.alibou.banking.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(
            @RequestBody UserRequest userRequest
    ) {
        userService.createUser(userRequest);
    }

    @PutMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> updateUser(
            @PathVariable("user-id") Long userId,
            @Valid @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        userService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        List<UserResponse> users = userService.findAllUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> findById(
            @PathVariable("user-id") Long userId
    ) {
        UserResponse user = userService.findById(userId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest,
            Authentication connectedUser
    ) {
        final long userId = ((User)connectedUser.getPrincipal()).getId();
        userService.changePassword(userId, changePasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
