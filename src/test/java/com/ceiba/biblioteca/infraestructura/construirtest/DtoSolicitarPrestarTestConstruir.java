package com.ceiba.biblioteca.infraestructura.construirtest;

import com.ceiba.biblioteca.aplicacion.dto.DtoSolicitudPrestarLibro;

public class DtoSolicitarPrestarTestConstruir {
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;

    public DtoSolicitarPrestarTestConstruir() {
        this.isbn = "DASD154212";
        this.identificacionUsuario = "154515485";
        this.tipoUsuario = 1;
    }

    public DtoSolicitarPrestarTestConstruir conIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public DtoSolicitarPrestarTestConstruir conIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
        return this;
    }

    public DtoSolicitarPrestarTestConstruir conTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        return this;
    }

    public DtoSolicitudPrestarLibro construir() {
        return new DtoSolicitudPrestarLibro(isbn, identificacionUsuario, tipoUsuario);
    }

}
