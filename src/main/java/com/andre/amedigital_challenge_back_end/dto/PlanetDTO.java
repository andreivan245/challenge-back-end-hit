package com.andre.amedigital_challenge_back_end.dto;

import com.andre.amedigital_challenge_back_end.entities.Planet;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public class PlanetDTO {

    private Long id;
    private String name;
    private String climate;
    private String terrain;
    private int appearedInFilms;

    public PlanetDTO(){
    }

    public PlanetDTO(Long id, String name, String climate, String terrain, int appearedInFilms) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.appearedInFilms = appearedInFilms;
    }

    public PlanetDTO(Planet planet) {
        id = planet.getId();
        name = planet.getName();
        climate = planet.getClimate();
        terrain = planet.getTerrain();
        appearedInFilms = planet.getAppearedInFilms();
    }

    public PlanetDTO(Optional<Planet> planet) {
        id = planet.get().getId();
        name = planet.get().getName();
        climate = planet.get().getClimate();
        terrain = planet.get().getTerrain();
        appearedInFilms = planet.get().getAppearedInFilms();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public int getAppearedInFilms() {
        return appearedInFilms;
    }

    public void setAppearedInFilms(int appearedInFilms) {
        this.appearedInFilms = appearedInFilms;
    }
}
