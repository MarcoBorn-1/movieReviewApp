package com.example.filmreviewapp.service;

import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.Movie;
import com.example.filmreviewapp.repository.ActorRepository;
import com.example.filmreviewapp.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ActorManager {
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;

    public Iterable<Actor> findAll() {
        return actorRepository.findAll();
    }


    public Optional<Actor> findById(Long actorID) {
        return actorRepository.findById(actorID);
    }

    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    public void deleteById(Long actorID) {
        actorRepository.deleteById(actorID);
    }

    public Actor update(Actor actor) {
        return actorRepository.save(actor);
    }


    public boolean addMovie(Long actorID, Long movieID) {
        Actor actor = findById(actorID).orElse(null);
        Movie movie = movieRepository.findById(movieID).orElse(null);
        if (actor != null && movie != null) {
            List<Movie> movieList;
            movieList = actor.getMovieList();
            if (movieList.contains(movie)) return false;
            movieList.add(movie);
            actor.setMovieList(movieList);
            save(actor);

            List<Actor> actorList;
            actorList = movie.getActorList();
            actorList.add(actor);
            movie.setActorList(actorList);
            movieRepository.save(movie);
            return true;
        }
        return false;
    }
}
