package com.digitalvideostore.videostoreapp.service;

import com.digitalvideostore.videostoreapp.model.Media;
import com.digitalvideostore.videostoreapp.repository.MediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    private MediaRepo repo;

    public Media save(Media media) {
        return repo.save(media);
    }

    public Optional<Media> getById(String id) {
        return repo.findById(id);
    }

    public List<Media> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    public List<Media> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    public List<Media> getFeatured(String category) {
        return repo.findByCategoryAndFeaturedTrue(category);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
