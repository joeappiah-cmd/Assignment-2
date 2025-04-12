package com.digitalvideostore.videostoreapp.service;

import com.digitalvideostore.videostoreapp.model.User;
import com.digitalvideostore.videostoreapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createAccount(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password before saving
        return repo.save(user);
    }

    public Optional<User> getUserByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Optional<User> fetchUser(String userId) {
        return repo.findById(userId);
    }
}
