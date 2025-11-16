package com.example.movie.mappers;

import com.example.movie.dto.ActorDto;
import com.example.movie.models.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorMapper {
    ActorMapper INSTANCE = Mappers.getMapper(ActorMapper.class);

    ActorDto toDto(Actor actor);
    Actor toEntity(ActorDto actorDto);
}