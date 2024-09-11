package com.andre.amedigital_challenge_back_end.services;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.dto.StarWarsResponseDTO;
import com.andre.amedigital_challenge_back_end.entities.Planet;
import com.andre.amedigital_challenge_back_end.exceptions.EntityNotFoundException;
import com.andre.amedigital_challenge_back_end.repositories.PlanetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@Service
public class PlanetService {

    @Autowired
    private PlanetRepository repository;

    @Autowired
    private RestTemplate restTemplate;


    public PlanetDTO findById(Long id) {
        Planet entity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id not found: " + id));

        return new PlanetDTO(entity);

    }

    public PlanetDTO findByName(String name) {
        Planet entity = repository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Name not found: " + name));
        return new PlanetDTO(entity);

    }

    public Planet addPlanet(PlanetDTO planetDTO){
        Planet entity = new Planet();
        BeanUtils.copyProperties(planetDTO, entity);
        entity.setAppearedInFilms(getStarWarsAPI(planetDTO.getName()));
        repository.save(entity);
        return entity;
    }

    public void deletePlanet(Long id){
        Planet entity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id not found " + id));

        repository.delete(entity);
    }

    public List<PlanetDTO> findAll() {
       return repository.findAll().stream().map(PlanetDTO::new).toList();
    }

    public int getStarWarsAPI(String planetName){
        int appearedInFilms = 0;

        String url = "https://swapi.dev/api/planets/?search=" + planetName;
        ResponseEntity<StarWarsResponseDTO> responseEntity = restTemplate.getForEntity(url, StarWarsResponseDTO.class);
        StarWarsResponseDTO responseBody = responseEntity.getBody();

        if(!responseBody.getResults().isEmpty()) {
            appearedInFilms = responseBody.getResults().get(0).getFilms().size();
            return appearedInFilms;
        }

        return appearedInFilms;

    }

}
