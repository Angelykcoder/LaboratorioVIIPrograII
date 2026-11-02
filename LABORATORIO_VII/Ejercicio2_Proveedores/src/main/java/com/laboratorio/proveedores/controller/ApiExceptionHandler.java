package com.laboratorio.proveedores.controller;

import com.laboratorio.proveedores.exception.ProveedorNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ProveedorNoEncontradoException.class)
    public ResponseEntity<Map<String, String>> noEncontrado(ProveedorNoEncontradoException exception) {
        return respuesta(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> datosInvalidos(MethodArgumentNotValidException exception) {
        String mensaje = exception.getBindingResult().getFieldErrors().stream()
                .findFirst().map(error -> error.getDefaultMessage()).orElse("Los datos enviados no son válidos");
        return respuesta(HttpStatus.BAD_REQUEST, mensaje);
    }

    private ResponseEntity<Map<String, String>> respuesta(HttpStatus estado, String mensaje) {
        Map<String, String> cuerpo = new LinkedHashMap<>();
        cuerpo.put("error", mensaje);
        return ResponseEntity.status(estado).body(cuerpo);
    }
}
