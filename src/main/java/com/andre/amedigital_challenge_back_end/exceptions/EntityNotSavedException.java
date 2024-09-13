package com.andre.amedigital_challenge_back_end.exceptions;

public class EntityNotSavedException extends RuntimeException {
    public EntityNotSavedException(String message) {
        super(message);
    }
}
