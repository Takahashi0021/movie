package com.example.movie.repositories;

import com.example.movie.models.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}