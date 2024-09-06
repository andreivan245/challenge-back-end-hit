package com.andre.amedigital_challenge_back_end.repositories;

import com.andre.amedigital_challenge_back_end.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    Optional<Planet> findByName(String name);
}
