package com.example.movies;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.movies.entities.Movie;
import com.example.movies.entities.Show;
import com.example.movies.repositories.MovieRepository;
import com.example.movies.repositories.ShowRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class MovieShowApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieShowApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(MovieRepository movieRepo, ShowRepository showRepo) {
        return args -> {

            Movie m1 = new Movie(null, "Inception", "Sci-fi heist", 148);
            Movie m2 = new Movie(null, "The Matrix", "Cyberpunk action", 136);
            Movie m3 = new Movie(null, "Interstellar", "Space exploration drama", 169);
            Movie m4 = new Movie(null, "Avatar", "Sci-fi fantasy", 162);
            Movie m5 = new Movie(null, "John Wick", "Action thriller", 101);

            movieRepo.save(m1);
            movieRepo.save(m2);
            movieRepo.save(m3);
            movieRepo.save(m4);
            movieRepo.save(m5);

            // Shows for movies
            showRepo.save(new Show(null, m1, LocalDateTime.now().plusDays(1).withHour(15).withMinute(0), "Screen 1"));
            showRepo.save(new Show(null, m1, LocalDateTime.now().plusDays(1).withHour(19).withMinute(0), "Screen 2"));

            showRepo.save(new Show(null, m2, LocalDateTime.now().plusDays(2).withHour(14).withMinute(30), "Screen 3"));
            showRepo.save(new Show(null, m2, LocalDateTime.now().plusDays(3).withHour(18).withMinute(0), "Screen 1"));

            showRepo.save(new Show(null, m3, LocalDateTime.now().plusDays(1).withHour(16).withMinute(45), "Screen 4"));

            showRepo.save(new Show(null, m4, LocalDateTime.now().plusDays(2).withHour(17).withMinute(30), "Screen 2"));
            showRepo.save(new Show(null, m4, LocalDateTime.now().plusDays(4).withHour(20).withMinute(15), "Screen 5"));

            showRepo.save(new Show(null, m5, LocalDateTime.now().plusDays(1).withHour(21).withMinute(0), "Screen 3"));
            showRepo.save(new Show(null, m5, LocalDateTime.now().plusDays(2).withHour(13).withMinute(15), "Screen 1"));

        };
    }
}