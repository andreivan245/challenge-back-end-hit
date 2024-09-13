package com.andre.amedigital_challenge_back_end.services;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.entities.Planet;
import com.andre.amedigital_challenge_back_end.exceptions.EntityListNotFoundException;
import com.andre.amedigital_challenge_back_end.exceptions.EntityNotFoundException;
import com.andre.amedigital_challenge_back_end.exceptions.EntityNotSavedException;
import com.andre.amedigital_challenge_back_end.repositories.PlanetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PlanetService {

    @Autowired
    private PlanetRepository repository;

    @Autowired
    private StarWarsAPIService starWarsAPIService;

    public PlanetDTO findById(Long id) {
        Optional<Planet> optionalEntityPlanet = repository.findById(id);

        Planet entityPlanet = optionalEntityPlanet.orElseThrow(
                    () -> new EntityNotFoundException("Id not found: " + id));

        return new PlanetDTO(entityPlanet);
    }

    public PlanetDTO findByName(String name) {
        Planet entityPlanet = repository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Name not found: " + name));

        return new PlanetDTO(entityPlanet);
    }

    public Planet addPlanet(PlanetDTO planetDTO){
        Planet entityPlanet = new Planet();

        BeanUtils.copyProperties(planetDTO, entityPlanet);
        try {

            entityPlanet.setAppearedInFilms(starWarsAPIService.getAppearedInFilmsfromStarWarsAPI(planetDTO.getName()));

            repository.save(entityPlanet);
        } catch (Exception exception) {

            throw new EntityNotSavedException("Error when trying to save to database");
        }

        return entityPlanet;
    }

    public void deletePlanet(Long id){
        Optional<Planet> optionalEntityPlanet = repository.findById(id);

        Planet entityPlanet = optionalEntityPlanet.orElseThrow(
                () -> new EntityNotFoundException("Id not found: " + id));

        repository.delete(entityPlanet);
    }

    public List<PlanetDTO> findAll() {
        try {
            return repository.findAll().stream().map(PlanetDTO::new).toList();
        } catch (Exception exception){
            throw new EntityListNotFoundException("Error when returning listing");
        }
    }

}
