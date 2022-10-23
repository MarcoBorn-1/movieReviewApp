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
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Director {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private Date birthDate;
    private String description;

    public Director(String name, String surname, Date birthDate, String description) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.description = description;
    }

    // Preventing infinite cascades in JSON
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToMany
    @JoinTable(name = "director_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "director_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Movie> movieList = new ArrayList<>();


    public void addNewMovie(Movie movie) {
        movieList.add(movie);
    }
}
