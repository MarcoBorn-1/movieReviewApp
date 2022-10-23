package com.example.filmreviewapp.repository;

import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository  extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
}
