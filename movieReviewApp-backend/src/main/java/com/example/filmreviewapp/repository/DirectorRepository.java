package com.example.filmreviewapp.repository;

import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository  extends JpaRepository<Director, Long> {
    List<Director> findDirectorByNameContainingIgnoreCase(String name);
    List<Director> findDirectorBySurnameContainingIgnoreCase(String name);
    Director findDirectorByNameAndSurnameContainingIgnoreCase(String name, String surname);
}
