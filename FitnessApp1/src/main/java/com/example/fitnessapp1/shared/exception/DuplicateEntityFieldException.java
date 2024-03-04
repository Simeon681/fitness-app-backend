package com.example.fitnessapp1.shared.exception;

public class DuplicateEntityFieldException extends RuntimeException {
    public DuplicateEntityFieldException(String message) {
        super(message);
    }
}
