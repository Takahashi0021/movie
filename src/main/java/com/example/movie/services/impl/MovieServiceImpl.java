package com.example.movie.services.impl;

import com.example.movie.dto.MovieDto;
import com.example.movie.models.Movie;
import com.example.movie.repositories.MovieRepository;
import com.example.movie.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto> movieDtoList = new ArrayList<>();

        movies.forEach(movie -> {
            MovieDto dto = toDto(movie);
            movieDtoList.add(dto);
        });

        return movieDtoList;
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (Objects.isNull(movie)) {
            return null;
        }
        return toDto(movie);
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = toEntity(movieDto);
        Movie createdMovie = movieRepository.save(movie);
        return toDto(createdMovie);
    }

    @Override
    public MovieDto updateMovie(Long id, MovieDto movieDto) {
        MovieDto existingMovie = getMovieById(id);
        if (Objects.isNull(existingMovie)) {
            return null;
        }

        Movie movie = toEntity(movieDto);
        movie.setId(id);
        Movie updatedMovie = movieRepository.save(movie);
        return toDto(updatedMovie);
    }

    @Override
    public boolean deleteMovie(Long id) {
        MovieDto existingMovie = getMovieById(id);
        if (Objects.isNull(existingMovie)) {
            return false;
        }

        movieRepository.deleteById(id);
        return true;
    }

    private MovieDto toDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .movieTitle(movie.getTitle())
                .movieDirector(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .genre(movie.getGenre())
                .duration(movie.getDuration())
                .build();
    }

    private Movie toEntity(MovieDto dto) {
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getMovieTitle());
        movie.setDirector(dto.getMovieDirector());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setGenre(dto.getGenre());
        movie.setDuration(dto.getDuration());
        return movie;
    }
}