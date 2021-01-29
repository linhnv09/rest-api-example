package com.ttcsolutions.studentmanager.exceptions;

import com.ttcsolutions.studentmanager.utils.StringResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {
    public static <T> ResponseEntity<SystemResponse<T>> ok() {
        return ResponseEntity.ok(new SystemResponse<>(200, StringResponses.SUCCESS));
    }

    public static <T> ResponseEntity<SystemResponse<T>> ok(T data) {
        return ResponseEntity.ok(new SystemResponse<>(200, StringResponses.SUCCESS, data));
    }

    public static <T> ResponseEntity<SystemResponse<T>> badRequest(String message) {
        return ResponseEntity
                .badRequest()
                .body(new SystemResponse<>(400, message));
    }
}
