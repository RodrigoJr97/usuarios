package com.rdg.pratica.usuarios.exceptions.handler;

import com.rdg.pratica.usuarios.exceptions.CampoDuplicadoException;
import com.rdg.pratica.usuarios.exceptions.CaracteresNaoPermitidosException;
import com.rdg.pratica.usuarios.exceptions.ElementoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(CaracteresNaoPermitidosException.class)
    public ResponseEntity<ExceptionResponse> handlerCaracteresNaoPermitidos(CaracteresNaoPermitidosException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                getURI(request));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CampoDuplicadoException.class)
    public ResponseEntity<ExceptionResponse> handlerCampoDuplicado(CampoDuplicadoException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                getURI(request));

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ElementoNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponse> handlerElementoNaoEncontrado(ElementoNaoEncontradoException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                getURI(request));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerRecursoNaoEncontrado(NoResourceFoundException ex, HttpServletRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                "URI informada não reconhecida",
                getURI(request));

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handlerValidacaoException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> camposComErro = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "Erro campo: " + fieldError.getField())
                .collect(Collectors.toList());

        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                camposComErro,
                getURI(request));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String getURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

}
