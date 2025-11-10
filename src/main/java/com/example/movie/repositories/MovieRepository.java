package com.example.movie.repositories;

import com.example.movie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}