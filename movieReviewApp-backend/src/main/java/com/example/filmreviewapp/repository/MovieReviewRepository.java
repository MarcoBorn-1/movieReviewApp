package com.example.filmreviewapp.repository;

import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.AppUser;
import com.example.filmreviewapp.entity.Movie;
import com.example.filmreviewapp.entity.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieReviewRepository  extends JpaRepository<MovieReview, Long> {
    @Query("SELECT AVG(e.rating) FROM MovieReview e WHERE e.movie = ?1")
    double getAverageReview(Movie movie);

    Optional<MovieReview> findMovieReviewByUserAndMovie(AppUser user, Movie movie);
}
