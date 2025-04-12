package com.digitalvideostore.videostoreapp.repository;

import com.digitalvideostore.videostoreapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email); // ✅ Ensure this method exists
}
