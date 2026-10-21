package com.efecto.web.mercado.victoria.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAllExceptions(
            Exception ex,
            HttpServletRequest request) {

        log.error("Error interno en {}",
                request.getRequestURI(), ex);

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Hubo un problema interno en el servidor"
        );

        problem.setTitle("Internal Server Error");
        problem.setInstance(
                URI.create(request.getRequestURI())
        );


        return problem;
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ProblemDetail handleRecursoNoEncontrado(
            RecursoNoEncontradoException ex,
            HttpServletRequest request) {

        log.warn("Recurso no encontrado: {}",
                ex.getMessage());

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problem.setTitle("Not Found");
        problem.setInstance(
                URI.create(request.getRequestURI())
        );

        return problem;
    }
}

