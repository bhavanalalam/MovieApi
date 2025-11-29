package com.example.movies.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "shows")
public class Movie {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Integer durationMinutes;

    public Movie() {}
    public Movie(Long id, String title, String description, Integer durationMinutes) {
        this.id = id; this.title = title; this.description = description; this.durationMinutes = durationMinutes;
    }
    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
}
