package com.example.movie.services;

import com.example.movie.dto.ActorDto;
import java.util.List;

public interface ActorService {
    List<ActorDto> getAllActors();
    ActorDto getActorById(Long id);
    ActorDto createActor(ActorDto actorDto);
    ActorDto updateActor(Long id, ActorDto actorDto);
    boolean deleteActor(Long id);
}