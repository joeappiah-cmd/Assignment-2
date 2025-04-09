package com.digitalvideostore.videostoreapp.repository;

import com.digitalvideostore.videostoreapp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
