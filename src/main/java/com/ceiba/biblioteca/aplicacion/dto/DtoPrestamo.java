package com.ceiba.biblioteca.aplicacion.dto;

public class DtoPrestamo extends DtoSolicitudPrestarLibro {

    private int id;
    private String fechaMaximaDevolucion;

    public DtoPrestamo(int id, String isbn, String identificacionUsuario, int tipoUsuario, String fechaMaximaDevolucion) {
        super(isbn,identificacionUsuario,tipoUsuario);
        this.id = id;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }
}
