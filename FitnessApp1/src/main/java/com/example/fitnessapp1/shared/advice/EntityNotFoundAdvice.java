package com.example.fitnessapp1.shared.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class EntityNotFoundAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
