package com.example.art_gallery.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArtworkNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ArtworkNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

}
