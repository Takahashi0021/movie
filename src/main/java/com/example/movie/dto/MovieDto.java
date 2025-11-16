package com.example.movie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class MovieDto {
    private Long id;
    private String title;
    private int releaseYear;
    private String genre;
    private int duration;
    private DirectorDto director;
    private List<ActorDto> actors;
}