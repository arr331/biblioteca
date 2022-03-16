package com.ceiba.biblioteca.infraestructura.excepcion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ManejadorExcepcion {
    @ExceptionHandler(ExcepcionMensaje.class)
    public ResponseEntity manejarExcepcion(ExcepcionMensaje e) {
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMensaje());
    }
}
