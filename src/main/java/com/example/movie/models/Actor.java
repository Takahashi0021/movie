package com.example.movie.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "actors")
@Getter
@Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "country", length = 50)
    private String country;

    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;

    public Actor() {}

    public Actor(String name, String birthDate, String country) {
        this.name = name;
        this.birthDate = birthDate;
        this.country = country;
    }
}