package com.digitalvideostore.videostoreapp.repository;

import com.digitalvideostore.videostoreapp.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepo extends MongoRepository<Movie, String> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByFeaturedTrue();
}
