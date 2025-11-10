package com.example.movie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieDto {
    private Long id;
    private String movieTitle;
    private String movieDirector;
    private int releaseYear;
    private String genre;
    private int duration;
}