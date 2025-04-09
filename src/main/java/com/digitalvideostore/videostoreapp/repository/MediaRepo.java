package com.digitalvideostore.videostoreapp.repository;

import com.digitalvideostore.videostoreapp.model.Media;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MediaRepo extends MongoRepository<Media, String> {
    List<Media> findByCategory(String category);
    List<Media> findByTitleContainingIgnoreCase(String title);
    List<Media> findByCategoryAndFeaturedTrue(String category);
}
