package com.ceiba.biblioteca.aplicacion.dto;

public class DtoSolicitudPrestarLibro {
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;

    public DtoSolicitudPrestarLibro(String isbn, String identificacionUsuario, int tipoUsuario) {
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }
}
