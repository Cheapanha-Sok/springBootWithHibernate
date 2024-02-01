package com.example.demo.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
@RestControllerAdvice
public class GlobalResponse {
    public record RestErrorResponse(int status, String message,
                                    LocalDateTime timestamp) {
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundException.class})
    GlobalResponse.RestErrorResponse handleCustomerNotFoundException(
            NotFoundException notFoundException) {
        return new GlobalResponse.RestErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                notFoundException.getMessage(),
                LocalDateTime.now());
    }

}
