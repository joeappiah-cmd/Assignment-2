package com.digitalvideostore.videostoreapp.controller;

import com.digitalvideostore.videostoreapp.model.Media;
import com.digitalvideostore.videostoreapp.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    @Autowired
    private MediaService service;

    @PostMapping
    public ResponseEntity<Media> addMedia(@RequestBody Media media) {
        return ResponseEntity.ok(service.save(media));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMediaById(@PathVariable String id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Media> getByCategory(@RequestParam String category) {
        return service.getByCategory(category);
    }

    @GetMapping("/search")
    public List<Media> search(@RequestParam String title) {
        return service.searchByTitle(title);
    }

    @GetMapping("/featured")
    public List<Media> featured(@RequestParam String category) {
        return service.getFeatured(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMedia(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Media deleted successfully.");
    }
}
