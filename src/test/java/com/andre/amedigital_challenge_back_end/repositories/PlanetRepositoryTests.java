package com.andre.amedigital_challenge_back_end.repositories;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.entities.Planet;
import com.sun.istack.NotNull;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
public class PlanetRepositoryTests {

    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get planet successfully from DB")
    void findByNameSuccess() {
        String name = "Terra";
        String climate = "Ariado";
        String terrain = "Variado";
        int appearedInFilms = 0;

        PlanetDTO data = new PlanetDTO(name,climate,terrain,appearedInFilms);
        this.createPlanet(data);

        Optional<Planet> foundedPlanet = this.planetRepository.findByName(name);

        assertThat(foundedPlanet.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get the planet successfully from DB")
    void findByNameFailure() {
        String name = "Terra";
        String climate = "Ariado";
        String terrain = "Variado";
        int appearedInFilms = 0;

        Optional<Planet> foundedPlanet = this.planetRepository.findByName(name);

        assertThat(foundedPlanet.isEmpty()).isTrue();
    }

    private Planet createPlanet(PlanetDTO data){
        Planet newPlanet = new Planet(data);
        this.entityManager.persist(newPlanet);
        return newPlanet;
    }
}
