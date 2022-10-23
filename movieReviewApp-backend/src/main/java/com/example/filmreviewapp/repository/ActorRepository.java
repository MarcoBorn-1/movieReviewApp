package com.example.filmreviewapp.repository;

import com.example.filmreviewapp.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    List<Actor> findActorByNameContainingIgnoreCase(String name);
    List<Actor> findActorBySurnameContainingIgnoreCase(String name);
    Actor findActorByNameAndSurnameContainingIgnoreCase(String name, String surname);
}
