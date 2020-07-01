package com.qualgo.masjids.exceptionhandlers;

import java.time.LocalDateTime;

import com.qualgo.masjids.exceptions.MasjidNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RESTExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleNotFoundException(final MasjidNotFoundException notFoundException) {
        final ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), notFoundException.getMessage(), LocalDateTime
                .now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleBadRequest(final RuntimeException runtimeException) {
        final ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), runtimeException.getMessage(), LocalDateTime
                .now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

