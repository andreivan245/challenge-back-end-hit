package com.andre.amedigital_challenge_back_end.services;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.entities.Planet;
import com.andre.amedigital_challenge_back_end.exceptions.EntityListNotFoundException;
import com.andre.amedigital_challenge_back_end.exceptions.EntityNotFoundException;
import com.andre.amedigital_challenge_back_end.exceptions.EntityNotSavedException;
import com.andre.amedigital_challenge_back_end.exceptions.handler.EntityNameAlreadyExistsException;
import com.andre.amedigital_challenge_back_end.repositories.PlanetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PlanetServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "Earth";
    private static final String CLIMATE = "Desert";
    private static final String TERRAIN = "Plateaus";
    private static final Integer APPEAREDINFILMS = 0;
    private static final Integer INDEX = 0;

    @Autowired
    @InjectMocks
    private PlanetService planetService;

    @Mock
    private PlanetRepository repository;

    @Mock
    private StarWarsAPIService starWarsAPIService;

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
        when(repository.findById(anyLong())).thenThrow(new EntityNotFoundException("Id not found: " + ID));

        Exception exception = assertThrows(EntityNotFoundException.class, () -> planetService.findById(ID));

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
        when(repository.findByName(anyString())).thenThrow(new EntityNotFoundException("Name not found: " + NAME));

        Exception exception = assertThrows(EntityNotFoundException.class, () -> planetService.findByName(NAME));

        assertEquals("Name not found: " + NAME, exception.getMessage());
    }



    @Test
    @DisplayName("Should save the planet in the database")
    void addPlanetSuccess() {
        when(repository.save(any())).thenReturn(planet);

        Planet savedPlanet = planetService.addPlanet(planetDTO);

        assertEquals(ID, savedPlanet.getId());
        assertEquals(NAME, savedPlanet.getName());
        assertEquals(CLIMATE, savedPlanet.getClimate());
        assertEquals(TERRAIN, savedPlanet.getTerrain());
        assertEquals(APPEAREDINFILMS, savedPlanet.getAppearedInFilms());

    }

    @Test
    @DisplayName("Should not save the planet in the database and should throw exception successfully")
    void addPlanetNotSaved() {
        when(repository.save(any())).thenThrow(new EntityNotSavedException("Error when trying to save to database"));

        Exception exception = assertThrows(EntityNotSavedException.class, () -> planetService.addPlanet(planetDTO));

        assertEquals("Error when trying to save to database", exception.getMessage());

    }

    @Test
    @DisplayName("Should not save the planet in the database and should throw exception successfully because the name of the planet has already been saved in the database")
    void addPlanetWithAlreadySavedName() {
        when(repository.existsByName(anyString())).thenReturn(true);
        when(repository.save(any())).thenThrow(new EntityNameAlreadyExistsException("Error when trying to add resource again"));

        Exception exception = assertThrows(EntityNameAlreadyExistsException.class, () -> planetService.addPlanet(planetDTO));

        assertEquals("Error when trying to add resource again", exception.getMessage());

    }

    @Test
    @DisplayName("Should delete the planet in the database")
    void deletePlanetSuccess() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(planet));
        doNothing().when(repository).deleteById(anyLong());
        planetService.deletePlanet(ID);
        verify(repository, times(1)).delete(planet);
    }

    @Test
    @DisplayName("Should not delete the planet in the database and should throw exception successfully")
    void deletePlanetNotDeleted() {
        when(repository.findById(anyLong())).thenThrow(new EntityNotFoundException("Id not found: " + ID));
        doNothing().when(repository).deleteById(anyLong());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> planetService.deletePlanet(ID));
        assertEquals("Id not found: " + ID,exception.getMessage());
    }

    @Test
    @DisplayName("Should find the all planets successfully")
    void findAllSuccess() {
        when(repository.findAll()).thenReturn(List.of(planet));

        List<PlanetDTO> response = planetService.findAll();

        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(PlanetDTO.class, response.get(INDEX).getClass());

        assertEquals(ID,response.get(INDEX).getId());
        assertEquals(NAME,response.get(INDEX).getName());
        assertEquals(CLIMATE,response.get(INDEX).getClimate());
        assertEquals(TERRAIN,response.get(INDEX).getTerrain());
        assertEquals(APPEAREDINFILMS,response.get(INDEX).getAppearedInFilms());
    }

    @Test
    @DisplayName("Should not find the all planets and should throw exception successfully")
    void findAllNotFound() {
        when(repository.findAll()).thenThrow(new EntityListNotFoundException("Error when returning listing"));

        Exception exception = assertThrows(EntityListNotFoundException.class, () -> planetService.findAll());

        assertEquals("Error when returning listing",exception.getMessage());
    }

}