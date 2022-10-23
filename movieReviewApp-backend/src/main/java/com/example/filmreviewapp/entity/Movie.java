package com.example.filmreviewapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int release_year;
    private int length; // minutes
    private String description;

    public Movie (String name, int release_year, int length, String description) {
        this.name = name;
        this.release_year = release_year;
        this.length = length;
        this.description = description;
        this.categoryList = new ArrayList<>();
        this.directorList = new ArrayList<>();
        this.actorList = new ArrayList<>();
    }

    // Preventing infinite cascades in JSON
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(mappedBy = "movieList")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Category> categoryList = new ArrayList<>();

    // Preventing infinite cascades in JSON
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(mappedBy = "movieList")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Director> directorList = new ArrayList<>();

    // Preventing infinite cascades in JSON
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany(mappedBy = "movieList")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Actor> actorList = new ArrayList<>();
    public void addNewActor(Actor actor) {
        actorList.add(actor);
    }

    public void addNewDirector(Director director) {
        directorList.add(director);
    }
}
