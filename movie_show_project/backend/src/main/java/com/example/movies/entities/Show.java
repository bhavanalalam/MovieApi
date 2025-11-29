package com.example.movies.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Show {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Movie movie;

    private LocalDateTime startTime;
    private String screen;

    public Show() {}
    public Show(Long id, Movie movie, LocalDateTime startTime, String screen) {
        this.id = id; this.movie = movie; this.startTime = startTime; this.screen = screen;
    }
    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public String getScreen() { return screen; }
    public void setScreen(String screen) { this.screen = screen; }
}
