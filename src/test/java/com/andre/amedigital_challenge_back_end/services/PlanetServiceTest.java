package com.andre.amedigital_challenge_back_end.services;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.entities.Planet;
import com.andre.amedigital_challenge_back_end.repositories.PlanetRepository;
import org.h2.table.Plan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class PlanetServiceTest {

    @Mock
    private PlanetRepository repository;

    @Autowired
    @InjectMocks
    private PlanetService planetService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByIdSuccess() {

        Planet mockPlanet = new Planet(1L,"Terra","Quente","Arenoso",0);
        PlanetDTO mockPlanetDTO = new PlanetDTO(mockPlanet);

        when(repository.findById(mockPlanetDTO.getId())).thenReturn(Optional.of(mockPlanet));

        PlanetDTO planetDTO = planetService.findById(mockPlanet.getId());

        assertEquals(mockPlanetDTO.getId(),planetDTO.getId());
        assertEquals(mockPlanetDTO.getName(),planetDTO.getName());
        assertEquals(mockPlanetDTO.getClimate(),planetDTO.getClimate());
        assertEquals(mockPlanetDTO.getTerrain(),planetDTO.getTerrain());
        assertEquals(mockPlanetDTO.getAppearedInFilms(),planetDTO.getAppearedInFilms());

    }

    @Test
    void findByIdNotFound() {
    }

    @Test
    void findByName() {
    }

    @Test
    void addPlanet() {
    }

    @Test
    void deletePlanet() {
    }

    @Test
    void findAll() {
    }

    @Test
    void getStarWarsAPI() {
    }
}