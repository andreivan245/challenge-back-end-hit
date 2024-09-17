package com.andre.amedigital_challenge_back_end.repositories;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.entities.Planet;
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

    private static final String NAME = "Earth";
    private static final String CLIMATE = "Desert";
    private static final String TERRAIN = "Plateaus";
    private static final Integer APPEAREDINFILMS = 0;


    @Autowired
    PlanetRepository planetRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    @DisplayName("Should get planet successfully from Database")
    void findByNameSuccess() {

        PlanetDTO data = new PlanetDTO(NAME,CLIMATE,TERRAIN,APPEAREDINFILMS);
        this.createPlanet(data);

        Optional<Planet> foundedPlanet = this.planetRepository.findByName(NAME);

        assertThat(foundedPlanet.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get the planet successfully from Database")
    void findByNameFailure() {

        Optional<Planet> foundedPlanet = this.planetRepository.findByName(NAME);

        assertThat(foundedPlanet.isEmpty()).isTrue();
    }


    @Test
    @DisplayName("Should get planet successfully from Database")
    void existsByNameTrue() {

        PlanetDTO data = new PlanetDTO(NAME,CLIMATE,TERRAIN,APPEAREDINFILMS);
        this.createPlanet(data);

        Boolean planetExists = this.planetRepository.existsByName(NAME);

        assertThat(planetExists).isTrue();

    }

    @Test
    @DisplayName("Should not get the planet successfully from Database")
    void existsByNameFalse() {

        Boolean planetExists = this.planetRepository.existsByName(NAME);

        assertThat(planetExists).isFalse();
    }

    private void createPlanet(PlanetDTO data){
        Planet newPlanet = new Planet(data);
        this.entityManager.persist(newPlanet);
    }

}
