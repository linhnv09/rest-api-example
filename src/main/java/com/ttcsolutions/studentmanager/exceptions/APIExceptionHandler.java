package com.ttcsolutions.studentmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(), httpStatus, e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler({EmptyException.class, NullException.class})
    public ResponseEntity<?> handleEmptyException(Exception e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(), httpStatus, e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e, WebRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(), httpStatus, e.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorResponse, httpStatus);
    }
}
