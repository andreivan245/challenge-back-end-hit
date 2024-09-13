package com.andre.amedigital_challenge_back_end.exceptions.handler;

import com.andre.amedigital_challenge_back_end.exceptions.EntityListNotFoundException;
import com.andre.amedigital_challenge_back_end.exceptions.EntityNotFoundException;
import com.andre.amedigital_challenge_back_end.exceptions.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFound(EntityNotFoundException e, HttpServletRequest request){
        ErrorMessage err = new ErrorMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(EntityListNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityListNotFound(EntityListNotFoundException e, HttpServletRequest request){
        ErrorMessage err = new ErrorMessage();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
