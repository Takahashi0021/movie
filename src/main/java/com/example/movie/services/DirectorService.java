package com.example.movie.services;

import com.example.movie.dto.DirectorDto;
import java.util.List;

public interface DirectorService {
    List<DirectorDto> getAllDirectors();
    DirectorDto getDirectorById(Long id);
    DirectorDto createDirector(DirectorDto directorDto);
    DirectorDto updateDirector(Long id, DirectorDto directorDto);
    boolean deleteDirector(Long id);
}