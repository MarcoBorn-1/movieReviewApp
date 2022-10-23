package com.example.filmreviewapp.dto;

import com.example.filmreviewapp.entity.Actor;
import com.example.filmreviewapp.entity.Category;
import com.example.filmreviewapp.entity.Director;
import com.example.filmreviewapp.entity.Movie;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
public class MovieDTO {
    private Long key;
    private String name;
    private int release_year;
    private int length; // minutes
    private String description;
    private String rating;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Category> categoryList;

    private String directorList;
    private String actorList;

    public MovieDTO(Movie movie, double rating) {
        this.key = movie.getId();
        this.name = movie.getName();
        this.release_year = movie.getRelease_year();
        this.length = movie.getLength();
        this.description = movie.getDescription();
        this.categoryList = movie.getCategoryList();
        this.directorList = convertDirectorListToString(movie.getDirectorList());
        this.actorList = convertActorListToString(movie.getActorList());
        this.rating = calculateRating(rating);
    }

    private String convertActorListToString(List<Actor> actorList) {
        StringBuilder fullNameList = new StringBuilder();
        boolean first = true;
        for (Actor actor : actorList) {
            if (!first) {
                fullNameList.append(", ");
            }
            else first = false;
            String fullName = "";
            fullName += actor.getName();
            fullName += " ";
            fullName += actor.getSurname();
            fullNameList.append(fullName);
        }

        return fullNameList.toString();
    }

    private String convertDirectorListToString(List<Director> directorList) {
        StringBuilder fullNameList = new StringBuilder();
        boolean first = true;
        for (Director director : directorList) {
            if (!first) {
                fullNameList.append(", ");
            }
            else first = false;
            String fullName = "";
            fullName += director.getName();
            fullName += " ";
            fullName += director.getSurname();
            fullNameList.append(fullName);
        }

        return fullNameList.toString();
    }

    public String calculateRating(double rating) {
        if (rating == -1) return "";
        else return String.valueOf(rating);
    }
}
