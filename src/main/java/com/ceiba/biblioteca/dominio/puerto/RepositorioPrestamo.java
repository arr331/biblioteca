package com.ceiba.biblioteca.dominio.puerto;

import com.ceiba.biblioteca.aplicacion.dto.DtoPrestamo;
import com.ceiba.biblioteca.aplicacion.dto.DtoResultadoPrestar;
import com.ceiba.biblioteca.dominio.modelo.Prestamo;

public interface RepositorioPrestamo {
    DtoResultadoPrestar prestar(Prestamo prestamo);
    DtoPrestamo obtenerPorId(int id);
    boolean existePrestamo(int id);
    boolean existePrestamoPorIdentificacionUsuario(String identificacionUsuario);
}
