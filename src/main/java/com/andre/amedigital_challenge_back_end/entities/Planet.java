package com.andre.amedigital_challenge_back_end.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_planet")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String climate;
    private String terrain;
    private int appearedInFilms;

    public Planet() {
    }

    public Planet(Long id, String name, String climate, String terrain, int appearedInFilms) {
        this.id = id;
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
        this.appearedInFilms = appearedInFilms;
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
