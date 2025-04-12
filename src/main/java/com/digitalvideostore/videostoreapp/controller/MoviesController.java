package com.digitalvideostore.videostoreapp.controller;

import com.digitalvideostore.videostoreapp.model.Movie;
import com.digitalvideostore.videostoreapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000", "https://cjv-project.vercel.app"})
@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    @Autowired
    private MovieService service;

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(service.save(movie));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public List<Movie> getallmovies() {
        return service.getallmovies();


    }
    @GetMapping("/search")
    public List<Movie> search(@RequestParam String title) {
        return service.searchByTitle(title);
  
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Movie deleted successfully.");
    }
}
