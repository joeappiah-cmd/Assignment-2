package com.digitalvideostore.videostoreapp.controller;

import com.digitalvideostore.videostoreapp.model.TvShow;
import com.digitalvideostore.videostoreapp.service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = {"http://localhost:3000", "https://cjv-project.vercel.app"})
@RestController
@RequestMapping("/api/tvshows")
public class TvShowController {

    @Autowired
    private TvShowService service;

    @PostMapping
    public ResponseEntity<TvShow> addTvShow(@RequestBody TvShow tvShow) {
        return ResponseEntity.ok(service.save(tvShow));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTvShowById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/all")
    public List<TvShow> getalltvshows() {
        return service.getalltvshows();
}


    @GetMapping("/search")
    public List<TvShow> search(@RequestParam String title) {
        return service.searchByTitle(title);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTvShow(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("TV Show deleted successfully.");
    }
}
