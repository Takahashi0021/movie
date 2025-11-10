package com.example.movie.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "movies")
@Entity
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Column(name = "director", nullable = false, length = 100)
    private String director;

    @Column(name = "release_year", nullable = false)
    private int releaseYear;

    @Column(name = "genre", length = 50)
    private String genre;

    @Column(name = "duration")
    private int duration;

    public Movie() {}

    public Movie(String title, String director, int releaseYear, String genre, int duration) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.duration = duration;
    }
}