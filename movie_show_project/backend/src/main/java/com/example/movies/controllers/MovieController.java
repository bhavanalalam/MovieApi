package com.example.movies.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.movies.entities.Movie;
import com.example.movies.repositories.MovieRepository;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieRepository repo;
    public MovieController(MovieRepository repo) { this.repo = repo; }

    @GetMapping
    public List<Movie> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Movie create(@RequestBody Movie m) { return repo.save(m); }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie m) {
        return repo.findById(id).map(existing -> {
            existing.setTitle(m.getTitle());
            existing.setDescription(m.getDescription());
            existing.setDurationMinutes(m.getDurationMinutes());
            repo.save(existing);
            return ResponseEntity.ok(existing);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
