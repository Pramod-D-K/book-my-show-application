package com.accio.book_my_show.Exceptions;

import com.accio.book_my_show.Responses.ErrorResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.InsufficientResourcesException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
        ErrorResponse errorResponse= ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .details(e.getMessage())
                .message("Error")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InsufficientResourcesException.class)
    public ResponseEntity<?> handleInsufficientResourcesException(InsufficientResourcesException e){
        ErrorResponse errorResponse= ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .details(e.getMessage())
                .message("Error")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<?>handleEmptyResultDataAccessException(EmptyResultDataAccessException e){
        ErrorResponse errorResponse= ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .details(e.getMessage())
                .message("Error")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception e){
        ErrorResponse errorResponse= ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .details(e.getMessage())
                .message("Error")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
