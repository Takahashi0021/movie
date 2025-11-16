package com.example.movie.services.impl;

import com.example.movie.dto.MovieDto;
import com.example.movie.models.Actor;
import com.example.movie.models.Director;
import com.example.movie.models.Movie;
import com.example.movie.repositories.ActorRepository;
import com.example.movie.repositories.DirectorRepository;
import com.example.movie.repositories.MovieRepository;
import com.example.movie.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;

    @Override
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie == null) {
            return null;
        }
        return toDto(movie);
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = toEntity(movieDto);

        if (movieDto.getDirector() != null && movieDto.getDirector().getId() != null) {
            Director director = directorRepository.findById(movieDto.getDirector().getId()).orElse(null);
            movie.setDirector(director);
        }

        Movie savedMovie = movieRepository.save(movie);
        return toDto(savedMovie);
    }

    @Override
    public MovieDto updateMovie(Long id, MovieDto movieDto) {
        Movie existingMovie = movieRepository.findById(id).orElse(null);
        if (existingMovie == null) {
            return null;
        }

        if (movieDto.getTitle() != null) {
            existingMovie.setTitle(movieDto.getTitle());
        }
        if (movieDto.getReleaseYear() != 0) {
            existingMovie.setReleaseYear(movieDto.getReleaseYear());
        }
        if (movieDto.getGenre() != null) {
            existingMovie.setGenre(movieDto.getGenre());
        }
        if (movieDto.getDuration() != 0) {
            existingMovie.setDuration(movieDto.getDuration());
        }

        if (movieDto.getDirector() != null && movieDto.getDirector().getId() != null) {
            Director director = directorRepository.findById(movieDto.getDirector().getId()).orElse(null);
            existingMovie.setDirector(director);
        }

        Movie updatedMovie = movieRepository.save(existingMovie);
        return toDto(updatedMovie);
    }

    @Override
    public boolean deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            return false;
        }
        movieRepository.deleteById(id);
        return true;
    }

    @Override
    public MovieDto addActorsToMovie(Long movieId, List<Long> actorIds) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            return null;
        }

        List<Actor> actors = actorRepository.findAllById(actorIds);
        movie.setActors(actors);
        Movie updatedMovie = movieRepository.save(movie);
        return toDto(updatedMovie);
    }

    private MovieDto toDto(Movie movie) {
        MovieDto.MovieDtoBuilder builder = MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .genre(movie.getGenre())
                .duration(movie.getDuration());

        if (movie.getDirector() != null) {
            builder.director(
                    com.example.movie.dto.DirectorDto.builder()
                            .id(movie.getDirector().getId())
                            .name(movie.getDirector().getName())
                            .country(movie.getDirector().getCountry())
                            .birthDate(movie.getDirector().getBirthDate())
                            .build()
            );
        }

        if (movie.getActors() != null && !movie.getActors().isEmpty()) {
            List<com.example.movie.dto.ActorDto> actorDtos = movie.getActors().stream()
                    .map(actor -> com.example.movie.dto.ActorDto.builder()
                            .id(actor.getId())
                            .name(actor.getName())
                            .birthDate(actor.getBirthDate())
                            .country(actor.getCountry())
                            .build())
                    .collect(Collectors.toList());
            builder.actors(actorDtos);
        } else {
            builder.actors(java.util.Collections.emptyList());
        }

        return builder.build();
    }

    private Movie toEntity(MovieDto dto) {
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setReleaseYear(dto.getReleaseYear());
        movie.setGenre(dto.getGenre());
        movie.setDuration(dto.getDuration());
        return movie;
    }
}