package com.scm.backend.scm_backend.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandeler {

  /*  @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new LinkedHashMap<>();

        // Extract constraint violation messages
        for (ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }*/
}
