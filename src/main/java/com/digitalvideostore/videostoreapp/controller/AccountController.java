package com.digitalvideostore.videostoreapp.controller;

import com.digitalvideostore.videostoreapp.model.User;
import com.digitalvideostore.videostoreapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(service.createAccount(user));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        boolean isValid = service.validateLogin(user.getEmail(), user.getPassword());
        return isValid ? ResponseEntity.ok("Welcome back!") : ResponseEntity.status(401).body("invalid credentials");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> retrieveUser(@PathVariable String userId) {
        return service.fetchUser(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
