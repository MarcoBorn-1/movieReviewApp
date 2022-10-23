package com.example.filmreviewapp.repository;

import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
