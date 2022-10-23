package com.example.filmreviewapp;

import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.Movie;
import com.example.filmreviewapp.repository.ActorRepository;
import com.example.filmreviewapp.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class FilmReviewAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmReviewAppApplication.class, args);
    }


}
