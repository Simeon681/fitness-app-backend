package com.example.fitnessapp1.shared.advice;

import com.example.fitnessapp1.shared.exception.ConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // This annotation allows us to handle exceptions across the whole application
public class ConflictAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConflictException.class) // This annotation allows us to handle a specific exception
    public ResponseEntity<?> handleEntityNotFound(Exception exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
