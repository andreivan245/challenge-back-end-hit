package com.andre.amedigital_challenge_back_end.services;


import com.andre.amedigital_challenge_back_end.dto.StarWarsResponseDTO;
import com.andre.amedigital_challenge_back_end.exceptions.ErrorWhenRequestStarWarsAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsAPIService {

    @Autowired
    private RestTemplate restTemplate;

    public int getAppearedInFilmsfromStarWarsAPI(String planetName){

            int appearedInFilms = 0;
            String url = "https://swapi.dev/api/planets/?search=" + planetName;

            try {
                ResponseEntity<StarWarsResponseDTO> responseEntity = restTemplate.getForEntity(url, StarWarsResponseDTO.class);

                if (responseEntity.hasBody() && responseEntity.getBody() != null) {

                    StarWarsResponseDTO responseBody = responseEntity.getBody();

                    if(responseBody.getResults().isEmpty()) {
                        return appearedInFilms;
                    }

                    appearedInFilms = responseBody.getResults().get(0).getFilms().size();

                }

                return appearedInFilms;

            } catch (Exception exception) {
                throw new ErrorWhenRequestStarWarsAPI("Error when request StarWarsAPI");
            }
    }
}
