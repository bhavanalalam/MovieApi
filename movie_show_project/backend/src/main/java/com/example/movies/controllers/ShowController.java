package com.example.movies.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.example.movies.entities.Show;
import com.example.movies.repositories.ShowRepository;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.entities.Movie;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = "*")
public class ShowController {

    private final ShowRepository showRepo;
    private final MovieRepository movieRepo;

    public ShowController(ShowRepository showRepo, MovieRepository movieRepo) {
        this.showRepo = showRepo; this.movieRepo = movieRepo;
    }

    @GetMapping
    public List<Show> list() { return showRepo.findAll(); }

    @GetMapping("/movie/{movieId}")
    public List<Show> byMovie(@PathVariable Long movieId) { return showRepo.findByMovieId(movieId); }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Show show) {
       
        Movie m = show.getMovie();
        if (m == null || m.getId() == null || !movieRepo.existsById(m.getId())) {
            return ResponseEntity.badRequest().body("Movie reference missing or invalid (provide movie.id).");
        }
        Show saved = showRepo.save(show);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { showRepo.deleteById(id); }
}
