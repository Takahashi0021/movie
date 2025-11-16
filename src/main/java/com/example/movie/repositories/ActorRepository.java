package com.example.movie.repositories;

import com.example.movie.models.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}