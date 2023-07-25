package com.users.users.application.controller;

import com.users.users.application.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    // Manera para usar ProblemDetail de SpringBoot 3

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ProblemDetail onError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        System.out.println(request.getMethod());
        // Si hay errores, construir una respuesta personalizada con los mensajes de error
        List<String> errorMessages = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }
        String err = errorMessages.toString();
        //ErrorResponse errorResponse = new ErrorResponse(err);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, err);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // Obtener información del error
        String errorMessage = ex.getMessage();

        // Crear una instancia de ErrorResponse con los detalles del error
        ErrorResponse errorResponse = new ErrorResponse(errorMessage);

        ex.printStackTrace();
        // Si la excepcion es un IOException devolvemos bad request, en caso contrario internal server error

        return ex instanceof IOException
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);

    }

}
