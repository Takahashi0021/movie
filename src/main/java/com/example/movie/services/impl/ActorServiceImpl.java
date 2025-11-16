package com.example.movie.services.impl;

import com.example.movie.dto.ActorDto;
import com.example.movie.models.Actor;
import com.example.movie.repositories.ActorRepository;
import com.example.movie.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public List<ActorDto> getAllActors() {
        return actorRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ActorDto getActorById(Long id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) {
            return null;
        }
        return toDto(actor);
    }

    @Override
    public ActorDto createActor(ActorDto actorDto) {
        Actor actor = toEntity(actorDto);
        Actor savedActor = actorRepository.save(actor);
        return toDto(savedActor);
    }

    @Override
    public ActorDto updateActor(Long id, ActorDto actorDto) {
        Actor existingActor = actorRepository.findById(id).orElse(null);
        if (existingActor == null) {
            return null;
        }

        if (actorDto.getName() != null) {
            existingActor.setName(actorDto.getName());
        }
        if (actorDto.getBirthDate() != null) {
            existingActor.setBirthDate(actorDto.getBirthDate());
        }
        if (actorDto.getCountry() != null) {
            existingActor.setCountry(actorDto.getCountry());
        }

        Actor updatedActor = actorRepository.save(existingActor);
        return toDto(updatedActor);
    }

    @Override
    public boolean deleteActor(Long id) {
        if (!actorRepository.existsById(id)) {
            return false;
        }
        actorRepository.deleteById(id);
        return true;
    }

    private ActorDto toDto(Actor actor) {
        return ActorDto.builder()
                .id(actor.getId())
                .name(actor.getName())
                .birthDate(actor.getBirthDate())
                .country(actor.getCountry())
                .build();
    }

    private Actor toEntity(ActorDto dto) {
        Actor actor = new Actor();
        actor.setId(dto.getId());
        actor.setName(dto.getName());
        actor.setBirthDate(dto.getBirthDate());
        actor.setCountry(dto.getCountry());
        return actor;
    }
}