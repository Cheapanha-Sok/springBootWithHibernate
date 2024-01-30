package com.example.demo.customsException;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    public record RestErrorResponse(int status, String message,
                                    LocalDateTime timestamp) {
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundHandler.class})
    RestErrorResponse handleCustomerNotFoundException(
            NotFoundHandler notFoundHandler) {
        return new RestErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                notFoundHandler.getMessage(),
                LocalDateTime.now());
    }
}
