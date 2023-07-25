package com.users.users.application.controller;

import com.users.users.application.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ExceptionController {
    /***
     * // Manera para usar solo ControllerAdvice y ProblemDetail de SpringBoot 3
     *
     @ExceptionHandler({Exception.class}) public ProblemDetail onError(Exception ex, HttpServletRequest request){
     System.out.println(request.getMethod());
     return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
     }
     ***/

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
