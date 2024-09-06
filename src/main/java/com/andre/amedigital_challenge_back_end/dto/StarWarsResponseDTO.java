package com.andre.amedigital_challenge_back_end.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Component
public class StarWarsResponseDTO extends RestTemplate {
    private int count;
    private String next;
    private String previous;
    private List<StarWarsDTO> results;

    public StarWarsResponseDTO(){
    }


    public  StarWarsResponseDTO(int count, String next, String previous, List<StarWarsDTO> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<StarWarsDTO> getResults() {
        return results;
    }

    public void setResults(List<StarWarsDTO> results) {
        this.results = results;
    }
}
