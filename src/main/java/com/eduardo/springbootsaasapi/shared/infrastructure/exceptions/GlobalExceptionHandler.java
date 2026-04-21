package com.eduardo.springbootsaasapi.shared.infrastructure.exceptions;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(BaseException.class)
        public ResponseEntity<ErrorResponse> handleBaseException(
                        BaseException ex,
                        HttpServletRequest request) {
                return ResponseEntity
                                .status(ex.getStatusCode())
                                .body(new ErrorResponse(
                                                ex.getStatusCode(),
                                                "Business Error",
                                                ex.getMessage(),
                                                request.getRequestURI(),
                                                LocalDateTime.now()));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleValidation(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {
                String message = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .findFirst()
                                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                                .orElse("Validation error");

                return ResponseEntity
                                .status(400)
                                .body(new ErrorResponse(
                                                400,
                                                "Validation Error",
                                                message,
                                                request.getRequestURI(),
                                                LocalDateTime.now()));
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGeneral(
                        Exception ex,
                        HttpServletRequest request) {
                return ResponseEntity
                                .status(500)
                                .body(new ErrorResponse(
                                                500,
                                                "Internal Server Error",
                                                ex.getMessage(),
                                                request.getRequestURI(),
                                                LocalDateTime.now()));
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<ErrorResponse> handleDatabaseError(
                        DataIntegrityViolationException ex,
                        HttpServletRequest request) {
                return ResponseEntity
                                .status(400)
                                .body(new ErrorResponse(
                                                400,
                                                "Database Error",
                                                "Duplicate or invalid data",
                                                request.getRequestURI(),
                                                LocalDateTime.now()));
        }

}
