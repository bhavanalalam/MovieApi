package com.example.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.movies.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
