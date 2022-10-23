package com.example.filmreviewapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@ToString
@Getter
@Setter
public class MovieReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Preventing infinite cascades in JSON
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AppUser user;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne
    @JoinColumn(name="movie_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;
    private double rating;

    public MovieReview(AppUser user, Movie movie, double rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }
    //private String comment;
}
