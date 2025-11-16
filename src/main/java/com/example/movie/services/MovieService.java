package com.example.movie.services;

import com.example.movie.dto.MovieDto;
import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovies();
    MovieDto getMovieById(Long id);
    MovieDto createMovie(MovieDto movieDto);
    MovieDto updateMovie(Long id, MovieDto movieDto);
    boolean deleteMovie(Long id);
    MovieDto addActorsToMovie(Long movieId, List<Long> actorIds); // НОВЫЙ МЕТОД
}