package com.example.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.movies.entities.Show;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByMovieId(Long movieId);
}
