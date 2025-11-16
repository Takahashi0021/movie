package com.example.movie.services.impl;

import com.example.movie.dto.DirectorDto;
import com.example.movie.mappers.DirectorMapper;
import com.example.movie.models.Director;
import com.example.movie.repositories.DirectorRepository;
import com.example.movie.services.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    @Override
    public List<DirectorDto> getAllDirectors() {
        return directorRepository.findAll().stream()
                .map(directorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDto getDirectorById(Long id) {
        Director director = directorRepository.findById(id).orElse(null);
        if (director == null) {
            return null;
        }
        return directorMapper.toDto(director);
    }

    @Override
    public DirectorDto createDirector(DirectorDto directorDto) {
        Director director = directorMapper.toEntity(directorDto);
        Director savedDirector = directorRepository.save(director);
        return directorMapper.toDto(savedDirector);
    }

    @Override
    public DirectorDto updateDirector(Long id, DirectorDto directorDto) {
        Director existingDirector = directorRepository.findById(id).orElse(null);
        if (existingDirector == null) {
            return null;
        }

        if (directorDto.getName() != null) {
            existingDirector.setName(directorDto.getName());
        }
        if (directorDto.getCountry() != null) {
            existingDirector.setCountry(directorDto.getCountry());
        }
        if (directorDto.getBirthDate() != null) {
            existingDirector.setBirthDate(directorDto.getBirthDate());
        }

        Director updatedDirector = directorRepository.save(existingDirector);
        return directorMapper.toDto(updatedDirector);
    }

    @Override
    public boolean deleteDirector(Long id) {
        if (!directorRepository.existsById(id)) {
            return false;
        }
        directorRepository.deleteById(id);
        return true;
    }
}