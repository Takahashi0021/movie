package com.example.movie.mappers;

import com.example.movie.dto.DirectorDto;
import com.example.movie.models.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {
    DirectorMapper INSTANCE = Mappers.getMapper(DirectorMapper.class);

    DirectorDto toDto(Director director);
    Director toEntity(DirectorDto directorDto);
}