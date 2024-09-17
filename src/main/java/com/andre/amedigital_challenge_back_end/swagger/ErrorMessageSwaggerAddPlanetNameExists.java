package com.andre.amedigital_challenge_back_end.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class ErrorMessageSwaggerAddPlanetNameExists implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(example =  "2024-09-17T02:43:29.324791Z")
    private Instant timestamp;
    @Schema(example =  "400")
    private Integer status;
    @Schema(example =  "Resource cannot be added again")
    private String error;
    @Schema(example =  "Error when trying to add resource again")
    private String message;
    @Schema(example =  "/planet")
    private String path;

    public ErrorMessageSwaggerAddPlanetNameExists(){
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
