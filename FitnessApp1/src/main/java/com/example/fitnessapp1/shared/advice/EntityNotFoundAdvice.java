package com.example.fitnessapp1.shared.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // This annotation allows us to handle exceptions across the whole application
public class EntityNotFoundAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class) // This annotation allows us to handle a specific exception
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
