package com.andre.amedigital_challenge_back_end.services;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.entities.Planet;
import com.andre.amedigital_challenge_back_end.exceptions.EntityNotFoundException;
import com.andre.amedigital_challenge_back_end.repositories.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


class PlanetServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Earth";
    private static final String CLIMATE = "Desert";
    private static final String TERRAIN = "Plateaus";
    private static final Integer APPEAREDINFILMS = 0;

    @Autowired
    @InjectMocks
    @Spy
    private PlanetService planetService;

    @Mock
    private PlanetRepository repository;

    @Mock
    private RestTemplate restTemplate;

    @Spy
    private PlanetService spyPlanetService = new PlanetService();

    private Planet planet;
    private PlanetDTO planetDTO;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        planet = new Planet(ID,NAME,CLIMATE,TERRAIN,APPEAREDINFILMS);
        planetDTO = new PlanetDTO(planet);
    }

    @Test
    @DisplayName("Should find the planet successfully using the id")
    void findByIdSuccess() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(planet));

        PlanetDTO planetDTOResponse = planetService.findById(ID);

        assertNotNull(planetDTOResponse);
        assertEquals(PlanetDTO.class, planetDTOResponse.getClass());
        assertEquals(ID, planetDTOResponse.getId());
        assertEquals(NAME, planetDTOResponse.getName());
        assertEquals(CLIMATE, planetDTOResponse.getClimate());
        assertEquals(TERRAIN,planetDTOResponse.getTerrain());
        assertEquals(APPEAREDINFILMS, planetDTOResponse.getAppearedInFilms());

    }

    @Test
    @DisplayName("Should not find the planet with the ID and should throw exception successfully")
    void findByIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            planetService.findById(ID);
        });

        assertEquals("Id not found: " + ID, exception.getMessage());
    }

    @Test
    @DisplayName("Should find the planet successfully using the name")
    void findByNameSuccess() {
        when(repository.findByName(anyString())).thenReturn(Optional.of(planet));

        PlanetDTO planetDTOResponse = planetService.findByName(NAME);

        assertNotNull(planetDTOResponse);
        assertEquals(PlanetDTO.class, planetDTOResponse.getClass());
        assertEquals(ID, planetDTOResponse.getId());
        assertEquals(NAME, planetDTOResponse.getName());
        assertEquals(CLIMATE, planetDTOResponse.getClimate());
        assertEquals(TERRAIN,planetDTOResponse.getTerrain());
        assertEquals(APPEAREDINFILMS, planetDTOResponse.getAppearedInFilms());
    }

    @Test
    @DisplayName("Should not find the planet with the name and should throw exception successfully")
    void findByNameNotFound() {
        when(repository.findByName(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            planetService.findByName(NAME);
        });

        assertEquals("Name not found: " + NAME, exception.getMessage());
    }

    @Test
    void addPlanetSuccess() {

        Planet entity = new Planet();



        doReturn(APPEAREDINFILMS).when(spyPlanetService).getStarWarsAPI(NAME);

        when(repository.save(any(Planet.class))).thenReturn(entity);
        System.out.println(planetDTO.getName());
        Planet savedPlanet = planetService.addPlanet(planetDTO);



        assertNotNull(savedPlanet);
        assertEquals(ID, savedPlanet.getId());
        assertEquals(NAME, savedPlanet.getName());
        assertEquals(CLIMATE, savedPlanet.getClimate());
        assertEquals(TERRAIN, savedPlanet.getTerrain());
        assertEquals(APPEAREDINFILMS, savedPlanet.getAppearedInFilms());

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