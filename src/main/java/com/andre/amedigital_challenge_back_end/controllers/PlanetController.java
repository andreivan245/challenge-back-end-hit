package com.andre.amedigital_challenge_back_end.controllers;

import com.andre.amedigital_challenge_back_end.dto.PlanetDTO;
import com.andre.amedigital_challenge_back_end.swagger.ErrorMessageSwaggerAddPlanetNameExists;
import com.andre.amedigital_challenge_back_end.swagger.ErrorMessageSwaggerGetName;
import com.andre.amedigital_challenge_back_end.swagger.PlanetDTOSwaggerPostRequestBody;
import com.andre.amedigital_challenge_back_end.exceptions.ErrorMessage;
import com.andre.amedigital_challenge_back_end.services.PlanetService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@OpenAPIDefinition(info = @Info(
        title = "PlanetAPI Documentation",
        description = "API for managing and querying planets in the Star Wars universe. " +
                "It allows you to perform CRUD (create, read, delete) operations on planets, as well as access specific details such as name" +
                ", climate, terrain and number of appearances in films. Integrates with the Star Wars public API to provide additional, up-to-date planet information.",
        version = "1.0.0"))
@RestController
@RequestMapping(value = "/planet")
public class PlanetController {

    @Autowired
    private PlanetService service;

    @Operation(description = "Search for planet by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return planet"),
            @ApiResponse(responseCode = "404", description = "No planet with this id was found", content =  @Content(
                    schema = @Schema(implementation = ErrorMessage.class)
                    ) )
    })
    @GetMapping(value = "/{id}")
    public PlanetDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Operation(description = "Search for planet by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return planet"),
            @ApiResponse(responseCode = "404", description = "No planet with this name was found",content = @Content(
                    schema = @Schema(implementation = ErrorMessageSwaggerGetName.class)
            ) )
    })
    @GetMapping(value = "/name/{name}")
    public PlanetDTO findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @Operation(description = "Add planet to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planet added successfully"),
            @ApiResponse(responseCode = "500", description = "Unable to add planet", content = @Content(
                    schema = @Schema(implementation = ErrorMessageSwaggerAddPlanetNameExists.class)
            ) )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(
            schema = @Schema(implementation = PlanetDTOSwaggerPostRequestBody.class)
    ))
    @PostMapping
    public void addPlanet(@RequestBody @Validated PlanetDTO planetDTO) {
        service.addPlanet(planetDTO);
    }

    @Operation(description = "Deletes planet to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Planet successfully deleted"),
            @ApiResponse(responseCode = "404", description = "No planet with this id was found")
    })
    @DeleteMapping(value = "/{id}")
    public void deletePlanet(@PathVariable Long id) {
        service.deletePlanet(id);
    }

    @Operation(description = "Search all planets in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return all planets in database")
    })
    @GetMapping
    public List<PlanetDTO> getAll() {
       return service.findAll();
    }

}
