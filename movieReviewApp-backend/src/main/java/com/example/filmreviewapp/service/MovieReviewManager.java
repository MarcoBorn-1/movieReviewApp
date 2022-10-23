package com.example.filmreviewapp.service;

import com.example.filmreviewapp.entity.AppUser;
import com.example.filmreviewapp.entity.Movie;
import com.example.filmreviewapp.entity.MovieReview;
import com.example.filmreviewapp.repository.MovieReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MovieReviewManager {
    private final AppUserManager userManager;
    private final MovieManager movieManager;
    private final MovieReviewRepository movieReviewRepository;

    public List<MovieReview> findAll() {
        return movieReviewRepository.findAll();
    }

    public Boolean addReview(Long userID, Long movieID, double rating) {
        AppUser user = userManager.findById(userID).orElse(null);
        if (user == null) return false;

        Movie movie = movieManager.findById(movieID).orElse(null);
        if (movie == null) return false;

        Optional<MovieReview> movieReviewOptional = movieReviewRepository.findMovieReviewByUserAndMovie(user, movie);
        if (movieReviewOptional.isPresent()) return false;

        MovieReview movieReview = new MovieReview(user, movie, rating);
        movieReviewRepository.save(movieReview);

        return true;
    }
}
