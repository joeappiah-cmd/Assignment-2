package com.digitalvideostore.videostoreapp.service;

import com.digitalvideostore.videostoreapp.model.TvShow;
import com.digitalvideostore.videostoreapp.repository.TvShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TvShowService {

    @Autowired
    private TvShowRepo repo;

    public TvShow save(TvShow tvShow) {
        return repo.save(tvShow);
    }

    public Optional<TvShow> getById(String id) {
        return repo.findById(id);
    }

    public List<TvShow> getalltvshows() {
        return repo.findAll();
    }
    public List<TvShow> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    public List<TvShow> getFeatured() {
        return repo.findByFeaturedTrue();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
