package com.alibou.banking.user;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user")

public class UserController {
    private UserService userService;
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@Valid @RequestBody UserRequest user) {
        userService.createUser(user);
    }

    @PutMapping ("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateRequest user) {
        userService.updateUser(id,user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok( userService.findAllUsers(10,10))  ;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok( userService.findById(id)) ;
    }

    @PutMapping("/password/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable("id") Long id, @Valid @RequestBody ChangePasswordRequest passwordRequest) {
        userService.changePassword(id,passwordRequest);
    }

}
