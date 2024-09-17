package com.andre.amedigital_challenge_back_end.swagger;

import com.andre.amedigital_challenge_back_end.entities.Planet;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;


@Component
public class PlanetDTOSwaggerPostRequestBody {

    @Schema(hidden = true)
    private Long id;
    @Schema(example = "Tatooine")
    private String name;
    @Schema(example = "Arid")
    private String climate;
    @Schema(example = "Desert")
    private String terrain;
    @Schema(hidden = true)
    private int appearedInFilms;

    public PlanetDTOSwaggerPostRequestBody(){
    }

    public PlanetDTOSwaggerPostRequestBody(String name, String climate, String terrain, int appearedInFilms) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.appearedInFilms = appearedInFilms;
    }

    public PlanetDTOSwaggerPostRequestBody(Planet planet) {
        id = planet.getId();
        name = planet.getName();
        climate = planet.getClimate();
        terrain = planet.getTerrain();
        appearedInFilms = planet.getAppearedInFilms();
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
