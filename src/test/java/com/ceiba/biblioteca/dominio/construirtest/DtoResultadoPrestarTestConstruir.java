package com.ceiba.biblioteca.dominio.construirtest;

import com.ceiba.biblioteca.aplicacion.dto.DtoResultadoPrestar;

public class DtoResultadoPrestarTestConstruir {

    private int id;
    private String fechaMaximaDevolucion;

    public DtoResultadoPrestarTestConstruir() {
        this.id = 1;
        this.fechaMaximaDevolucion = "15/02/2022";
    }

    public DtoResultadoPrestarTestConstruir conFechaMaxima(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
        return this;
    }

    public DtoResultadoPrestar construir() {
        return new DtoResultadoPrestar(this.id, this.fechaMaximaDevolucion);
    }
}
