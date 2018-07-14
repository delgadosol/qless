package com.qless.challenge.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleServerException(Exception ex) {
        return new ResponseEntity<>(new ApiError("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
