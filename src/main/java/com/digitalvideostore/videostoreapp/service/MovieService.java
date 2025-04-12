package com.digitalvideostore.videostoreapp.service;

import com.digitalvideostore.videostoreapp.model.Movie;
import com.digitalvideostore.videostoreapp.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo repo;

    public Movie save(Movie movie) {
        return repo.save(movie);
    }

    public Optional<Movie> getById(String id) {
        return repo.findById(id);
    }

    public List<Movie> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> getFeatured() {
        return repo.findByFeaturedTrue();
    }

   public List<Movie> getallmovies() {
        return repo.findAll();
   }
    public void delete(String id) {
        repo.deleteById(id);
    }
}
