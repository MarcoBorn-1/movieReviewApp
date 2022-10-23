package com.example.filmreviewapp.service;

import com.example.filmreviewapp.dto.MovieDTO;
import com.example.filmreviewapp.entity.*;
import com.example.filmreviewapp.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class MovieManager {
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final MovieReviewRepository movieReviewRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Long movieID) {
        return movieRepository.findById(movieID);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteById(Long movieID) {
        movieRepository.deleteById(movieID);
    }

    public boolean addCategory(Long movieID, Long categoryID) {
        Movie movie = movieRepository.findById(movieID).orElse(null);
        Category category = categoryRepository.findById(categoryID).orElse(null);
        if (movie != null && category != null) {
            List<Category> categoryList;
            categoryList = movie.getCategoryList();
            if (categoryList.contains(category)) return false;
            categoryList.add(category);
            save(movie);

            List<Movie> movieList;
            movieList = category.getMovieList();
            movieList.add(movie);
            category.setMovieList(movieList);
            categoryRepository.save(category);

            return true;
        }
        return false;
    }

    public boolean addActor(Long movieID, Actor actor) {
        Movie movie = movieRepository.getById(movieID);
        Actor actor1 = actorRepository.findActorByNameAndSurnameContainingIgnoreCase(actor.getName(), actor.getSurname());
        if (actor1 == null) {
            actorRepository.save(actor);

            List<Actor> actorList = movie.getActorList();
            actorList.add(actor);
            movie.setActorList(actorList);
            movieRepository.save(movie);

            List<Movie> movieList = actor.getMovieList();
            movieList.add(movie);
            actor.setMovieList(movieList);
            actorRepository.save(actor);
        }
        else {
            List<Actor> actorList = movie.getActorList();
            actorList.add(actor1);
            movie.setActorList(actorList);
            movieRepository.save(movie);

            List<Movie> movieList = actor1.getMovieList();
            movieList.add(movie);
            actor1.setMovieList(movieList);
            actorRepository.save(actor1);

            movie.addNewActor(actor1);
            movieRepository.save(movie);
        }

        return true;
    }

    public boolean addDirector(Long movieID, Director director) {
        Movie movie = movieRepository.getById(movieID);
        Director director1 = directorRepository.findDirectorByNameAndSurnameContainingIgnoreCase(director.getName(), director.getSurname());
        if (director1 == null) {
            directorRepository.save(director);

            List<Director> directorList = movie.getDirectorList();
            directorList.add(director);
            movie.setDirectorList(directorList);
            movieRepository.save(movie);

            List<Movie> movieList = director.getMovieList();
            movieList.add(movie);
            director.setMovieList(movieList);
            directorRepository.save(director);
        }
        else {
            List<Director> directorList = movie.getDirectorList();
            directorList.add(director1);
            movie.setDirectorList(directorList);
            movieRepository.save(movie);

            List<Movie> movieList = director1.getMovieList();
            movieList.add(movie);
            director1.setMovieList(movieList);
            directorRepository.save(director1);

            movie.addNewDirector(director1);
            movieRepository.save(movie);
        }

        return true;
    }

    public List<MovieDTO> searchForMovie(String name) {
        List<Movie> titleSearch = movieRepository.findByNameContainingIgnoreCase(name);

        List<Actor> actorListName = actorRepository.findActorByNameContainingIgnoreCase(name);
        List<Actor> actorListSurname = actorRepository.findActorBySurnameContainingIgnoreCase(name);

        List<Director> directorListName = directorRepository.findDirectorByNameContainingIgnoreCase(name);
        List<Director> directorListSurname = directorRepository.findDirectorBySurnameContainingIgnoreCase(name);

        Set<Actor> actorSet = new LinkedHashSet<>(actorListName);
        actorSet.addAll(actorListSurname);
        List<Actor> actorList = new ArrayList<>(actorSet);

        Set<Director> directorSet = new LinkedHashSet<>(directorListName);
        directorSet.addAll(directorListSurname);
        List<Director> directorList = new ArrayList<>(directorSet);

        Set<Movie> movieList = new LinkedHashSet<>(titleSearch);

        for (Actor actor : actorList) {
            movieList.addAll(actor.getMovieList());
        }

        for(Director director : directorList) {
            movieList.addAll(director.getMovieList());
        }

        List<MovieDTO> movieDTOList = new ArrayList<>();

        for (Movie movie : movieList) {
            movieDTOList.add(new MovieDTO(movie, calculateRating(movie)));
        }

        return new ArrayList<>(movieDTOList);
    }

    private double calculateRating(Movie movie) {
        try {
            return movieReviewRepository.getAverageReview(movie);
        }
        catch (Exception e) {
            return -1;
        }
    }
}
