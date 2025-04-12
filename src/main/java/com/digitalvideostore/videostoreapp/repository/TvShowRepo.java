package com.digitalvideostore.videostoreapp.repository;

import com.digitalvideostore.videostoreapp.model.TvShow;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TvShowRepo extends MongoRepository<TvShow, String> {
    List<TvShow> findByTitleContainingIgnoreCase(String title);
    List<TvShow> findByFeaturedTrue();
}
