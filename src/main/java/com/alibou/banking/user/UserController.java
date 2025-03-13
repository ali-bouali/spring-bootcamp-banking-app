package com.alibou.banking.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid  UserRequest user) {
        userService.createUser(user);
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size

    ) {
        return ResponseEntity.ok( userService.findAllUsers(page, size));
    }
    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }
    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateUser(
            @RequestBody @Valid UserUpdateRequest userUpdateRequest,
            Long userId
    ) {
         userService.updateUser(userId,userUpdateRequest);
    }
    @PatchMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(
            @RequestBody @Valid ChangePasswordRequest passwordRequest,
            Long userId
    ){
        userService.changePassword(userId,passwordRequest);
    }


}
