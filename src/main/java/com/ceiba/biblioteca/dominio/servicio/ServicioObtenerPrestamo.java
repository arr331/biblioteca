package com.ceiba.biblioteca.dominio.servicio;

import com.ceiba.biblioteca.aplicacion.dto.DtoPrestamo;
import com.ceiba.biblioteca.dominio.puerto.RepositorioPrestamo;
import com.ceiba.biblioteca.infraestructura.excepcion.ExcepcionMensaje;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ServicioObtenerPrestamo {
    private final RepositorioPrestamo repositorioPrestamo;

    private static final String MENSAJE_NO_EXISTE_PRESTAMO = "No existe un préstamo con este criterio de búsqueda.";

    public ServicioObtenerPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public DtoPrestamo ejecutar(int id) {
        verificarExistePrestamo(id);
        return repositorioPrestamo.obtenerPorId(id);
    }

    private void verificarExistePrestamo(int id) {
        if (!repositorioPrestamo.existePrestamo(id)) {
            throw new ExcepcionMensaje(HttpStatus.NOT_FOUND, MENSAJE_NO_EXISTE_PRESTAMO);
        }
    }
}
