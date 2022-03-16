package com.ceiba.biblioteca.aplicacion.servicio;

import com.ceiba.biblioteca.aplicacion.dto.DtoPrestamo;
import com.ceiba.biblioteca.dominio.servicio.ServicioObtenerPrestamo;
import org.springframework.stereotype.Service;

@Service
public class ServicioAplicacionObtenerPrestamo {
    private final ServicioObtenerPrestamo servicioObtenerPrestamo;

    public ServicioAplicacionObtenerPrestamo(ServicioObtenerPrestamo servicioObtenerPrestamo) {
        this.servicioObtenerPrestamo = servicioObtenerPrestamo;
    }

    public DtoPrestamo ejecutar(int id) {
        return servicioObtenerPrestamo.ejecutar(id);
    }
}
