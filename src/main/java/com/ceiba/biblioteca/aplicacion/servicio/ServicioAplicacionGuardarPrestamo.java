package com.ceiba.biblioteca.aplicacion.servicio;

import com.ceiba.biblioteca.aplicacion.dto.DtoSolicitudPrestarLibro;
import com.ceiba.biblioteca.aplicacion.dto.DtoResultadoPrestar;
import com.ceiba.biblioteca.dominio.modelo.Prestamo;
import com.ceiba.biblioteca.dominio.servicio.ServicioGuardarPrestamo;
import org.springframework.stereotype.Service;

@Service
public class ServicioAplicacionGuardarPrestamo {
    private final ServicioGuardarPrestamo servicioGuardarPrestamo;

    public ServicioAplicacionGuardarPrestamo(ServicioGuardarPrestamo servicioGuardarPrestamo) {
        this.servicioGuardarPrestamo = servicioGuardarPrestamo;
    }

    public DtoResultadoPrestar ejecutar(DtoSolicitudPrestarLibro dto) {
        Prestamo prestamo = Prestamo.of(dto.getIsbn(), dto.getIdentificacionUsuario(), dto.getTipoUsuario());
        return servicioGuardarPrestamo.ejecutar(prestamo);
    }
}
