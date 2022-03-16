package com.ceiba.biblioteca.dominio.construirtest;

import com.ceiba.biblioteca.aplicacion.dto.DtoPrestamo;
import com.ceiba.biblioteca.dominio.modelo.Prestamo;

public class PrestamoTestConstruir {
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private String fechaMaximaDevolucion;

    public PrestamoTestConstruir() {
        this.isbn = "DASD154212";
        this.identificacionUsuario = "154515485";
        this.tipoUsuario = 1;
        this.fechaMaximaDevolucion = "01/02/2022";
    }

    public PrestamoTestConstruir conIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public PrestamoTestConstruir conIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
        return this;
    }

    public PrestamoTestConstruir conTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
        return this;
    }

    public Prestamo construir() {
        return Prestamo.of(isbn, identificacionUsuario, tipoUsuario);
    }

    public DtoPrestamo construirDto() {
        return new DtoPrestamo(1, isbn, identificacionUsuario, tipoUsuario, fechaMaximaDevolucion);
    }
}
