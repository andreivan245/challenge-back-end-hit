package com.andre.amedigital_challenge_back_end.swagger;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class ErrorMessageSwaggerGetName implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(example =  "2024-09-17T02:01:18.376615500Z")
    private Instant timestamp;
    @Schema(example =  "404")
    private Integer status;
    @Schema(example =  "Resource not found")
    private String error;
    @Schema(example =  "Name not found: Earth")
    private String message;
    @Schema(example =  "/planet/1")
    private String path;

    public ErrorMessageSwaggerGetName(){
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
