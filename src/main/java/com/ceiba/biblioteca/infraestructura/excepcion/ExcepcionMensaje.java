package com.ceiba.biblioteca.infraestructura.excepcion;

import org.springframework.http.HttpStatus;

public class ExcepcionMensaje  extends RuntimeException {
    private HttpStatus httpStatus;
    private Mensaje mensaje;

    public ExcepcionMensaje(HttpStatus httpStatus, String mensaje) {
        super(mensaje);
        this.mensaje = new Mensaje(mensaje);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }
}
