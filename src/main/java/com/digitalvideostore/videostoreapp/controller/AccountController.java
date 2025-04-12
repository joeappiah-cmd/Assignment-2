package com.digitalvideostore.videostoreapp.controller;

import com.digitalvideostore.videostoreapp.model.User;
import com.digitalvideostore.videostoreapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "https://cjv-project.vercel.app"})
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountService service;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(service.createAccount(user));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        Optional<User> foundUser = service.getUserByEmail(user.getEmail());

        if (foundUser.isPresent() && passwordEncoder.matches(user.getPassword(), foundUser.get().getPassword())) {
            return ResponseEntity.ok(foundUser.get());
        } else {
            return ResponseEntity.status(401).body("{\"error\":\"Invalid email or password\"}");
        }
    }

    public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}


   
    @GetMapping("/profile/{email}")
    public ResponseEntity<?> getUserProfile(@PathVariable String email) {
        Optional<User> user = service.getUserByEmail(email);
        
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body("{\"error\":\"User not found\"}");
        }
    
    }
}