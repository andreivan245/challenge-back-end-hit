package com.andre.amedigital_challenge_back_end.exceptions.handler;

public class EntityNameAlreadyExistsException extends RuntimeException{
    public EntityNameAlreadyExistsException(String message) {
        super(message);
    }
}
