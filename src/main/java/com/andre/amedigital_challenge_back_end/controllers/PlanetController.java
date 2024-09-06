package com.andre.amedigital_challenge_back_end.controllers;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/planet")
public class PlanetController {

    @Autowired
    private PlanetService service;

    @GetMapping(value = "/{id}")
    public PlanetDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/name/{name}")
    public PlanetDTO findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PostMapping
    public void addPlanet(@RequestBody @Validated PlanetDTO planetDTO) {
        service.addPlanet(planetDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePlanet(@PathVariable Long id) {
        service.deletePlanet(id);
    }

    @GetMapping
    public List<PlanetDTO> getAll() {
       return service.findAll();
    }

}
