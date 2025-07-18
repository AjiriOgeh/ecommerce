package com.ecommerce.orderService.exceptions;

import com.ecommerce.orderService.dtos.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ApiResponse> handleException(OrderNotFoundException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(exception.getMessage(), false));
    }

    @ExceptionHandler
    public final ResponseEntity<ApiResponse> handleException(ProductNotFoundException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(exception.getMessage(), false));
    }

    @ExceptionHandler
    public final ResponseEntity<ApiResponse> handleException(InsufficientProductQuantityException exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(exception.getMessage(), false));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ApiResponse> handleException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(errors, false));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiResponse> handleException(final Exception exception) {
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ApiResponse(exception.getMessage(), false));
    }
}
