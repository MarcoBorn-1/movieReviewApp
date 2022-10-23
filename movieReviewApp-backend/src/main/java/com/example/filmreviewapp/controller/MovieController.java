package com.example.filmreviewapp.controller;

import com.example.filmreviewapp.dto.MovieDTO;
import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.Director;
import com.example.filmreviewapp.entity.Movie;
import com.example.filmreviewapp.service.MovieManager;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping(path = "/api/movies")
public class MovieController {
    private final MovieManager movieManager;
    @GetMapping("/get/all")
    public ResponseEntity<List<Movie>> getAll(){
        List<Movie> movieList =  movieManager.findAll();
        return ResponseEntity.ok(movieList);
    }

    @GetMapping(value = "/get/id/{movieID}")
    public ResponseEntity<Movie> getById(@PathVariable("movieID") Long actorID) {
        Movie movie = movieManager.findById(actorID).orElse(null);
        return ResponseEntity.ok(movie);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save")
    public Movie add(@RequestBody Movie movie){
        return movieManager.save(movie);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/update")
    public Movie update(@RequestBody Movie movie) {
        return movieManager.save(movie);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/del/{movieID}")
    public ResponseEntity<String> delete(@PathVariable("movieID") Long movieID) {
        movieManager.deleteById(movieID);
        return ResponseEntity.ok("true");
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> searchForMovie(@Param("name") String name) {
        List<MovieDTO> movieList = movieManager.searchForMovie(name);
        return ResponseEntity.ok(movieList);
    }

    @PutMapping("/update/{movieID}/addCategory/{categoryID}")
    public boolean addCategory(@PathVariable("movieID") Long movieID, @PathVariable("categoryID") Long categoryID) {
        return movieManager.addCategory(movieID, categoryID);
    }

    @PostMapping("/update/{movieID}/addActor")
    public ResponseEntity<Boolean> addActor(@PathVariable("movieID") Long movieID, @RequestBody Actor actor) {
        return ResponseEntity.ok(movieManager.addActor(movieID, actor));
    }

    @PostMapping("/update/{movieID}/addDirector")
    public ResponseEntity<Boolean> addDirector(@PathVariable("movieID") Long movieID, @RequestBody Director director) {
        return ResponseEntity.ok(movieManager.addDirector(movieID, director));
    }
}
