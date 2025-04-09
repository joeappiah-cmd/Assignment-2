package com.digitalvideostore.videostoreapp.service;

import com.digitalvideostore.videostoreapp.model.User;
import com.digitalvideostore.videostoreapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserRepo repo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createAccount(User inputUser) {
        String hashed = passwordEncoder.encode(inputUser.getPassword());
        inputUser.setPassword(hashed);
        return repo.save(inputUser);
    }

    public boolean validateLogin(String email, String plainPassword) {
        Optional<User> user = repo.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(plainPassword, user.get().getPassword());
    }

    public Optional<User> fetchUser(String id) {
        return repo.findById(id);
    }
}
