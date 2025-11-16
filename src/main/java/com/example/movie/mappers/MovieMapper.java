package com.example.movie.mappers;

import com.example.movie.dto.MovieDto;
import com.example.movie.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "director", source = "director")
    @Mapping(target = "actors", source = "actors")
    MovieDto toDto(Movie movie);

    @Mapping(target = "director", source = "director")
    @Mapping(target = "actors", source = "actors")
    Movie toEntity(MovieDto movieDto);
}