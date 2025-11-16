package com.example.movie.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DirectorDto {
    private Long id;
    private String name;
    private String country;
    private String birthDate;
}