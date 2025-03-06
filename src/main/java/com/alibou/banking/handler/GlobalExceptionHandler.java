package com.alibou.banking.handler;

import com.alibou.banking.exceptions.TransactionException;
import com.alibou.banking.transaction.Transaction;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException exp) {
        List<String> errors = new ArrayList<>();
        exp.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    errors.add(fieldName + " - " + error.getDefaultMessage());
                });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<?> handleException(TransactionException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

}
