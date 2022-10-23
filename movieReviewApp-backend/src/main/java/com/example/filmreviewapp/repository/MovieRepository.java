package com.example.filmreviewapp.repository;

import com.example.filmreviewapp.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, Long> {
    List<Movie> findByNameContainingIgnoreCase(String name);
}
