package com.example.filmreviewapp.controller;

import com.example.filmreviewapp.entity.Movie;
import com.example.filmreviewapp.entity.MovieReview;
import com.example.filmreviewapp.service.MovieReviewManager;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/movieReviews")
public class MovieReviewController {
    MovieReviewManager movieReviewManager;

    @GetMapping("/get/all")
    public ResponseEntity<List<MovieReview>> getAll(){
        List<MovieReview> movieReviewList =  movieReviewManager.findAll();
        return ResponseEntity.ok(movieReviewList);
    }

    @GetMapping("/add/{userId}/{movieId}/{rating}")
    public ResponseEntity<Boolean> addReview(@PathVariable("userId") Long userID,
                                             @PathVariable("movieId") Long movieID, @PathVariable("rating") double rating) {
        return ResponseEntity.ok(movieReviewManager.addReview(userID, movieID, rating));
    }
}
