package com.andre.amedigital_challenge_back_end.services;

import com.andre.amedigital_challenge_back_end.dto.StarWarsResponseDTO;
import com.andre.amedigital_challenge_back_end.exceptions.ErrorWhenRequestStarWarsAPI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ActiveProfiles("test")
class StarWarsAPIServiceTest {

    private static final String NAME = "Earth";
    private static final Integer APPEAREDINFILMS = 0;

    @Autowired
    @InjectMocks
    private StarWarsAPIService starWarsAPIService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return from the StarWarsAPI the number of times the planet appeared in films")
    void getAppearedInFilmsfromStarWarsAPISuccess() {
        StarWarsResponseDTO responseDTO = new StarWarsResponseDTO();
        responseDTO.setResults(Collections.emptyList());

        when(restTemplate.getForEntity(anyString(), eq(StarWarsResponseDTO.class))).thenReturn(new ResponseEntity<>(responseDTO, HttpStatus.OK));

        int appearedInFilms = starWarsAPIService.getAppearedInFilmsfromStarWarsAPI(NAME);
        assertEquals(APPEAREDINFILMS, appearedInFilms);
    }

    @Test
    @DisplayName("Should not return from the StarWarsAPI the number of times the planet appeared in films and should throw exception successfully")
    void getAppearedInFilmsfromStarWarsAPIFalid() {

        when(restTemplate.getForEntity(anyString(), eq(StarWarsResponseDTO.class))).thenThrow(new ErrorWhenRequestStarWarsAPI("Error when request StarWarsAPI"));

        Exception exception = assertThrows(ErrorWhenRequestStarWarsAPI.class, () -> starWarsAPIService.getAppearedInFilmsfromStarWarsAPI(NAME));

        assertEquals("Error when request StarWarsAPI",exception.getMessage());

    }

}