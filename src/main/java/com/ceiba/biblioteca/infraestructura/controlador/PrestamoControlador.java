package com.ceiba.biblioteca.infraestructura.controlador;


import com.ceiba.biblioteca.aplicacion.dto.DtoPrestamo;
import com.ceiba.biblioteca.aplicacion.dto.DtoSolicitudPrestarLibro;
import com.ceiba.biblioteca.aplicacion.dto.DtoResultadoPrestar;
import com.ceiba.biblioteca.aplicacion.servicio.ServicioAplicacionGuardarPrestamo;
import com.ceiba.biblioteca.aplicacion.servicio.ServicioAplicacionObtenerPrestamo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {
    private final ServicioAplicacionGuardarPrestamo servicioAplicacionGuardarPrestamo;
    private final ServicioAplicacionObtenerPrestamo servicioAplicacionObtenerPrestamo;

    public PrestamoControlador(ServicioAplicacionGuardarPrestamo servicioAplicacionGuardarPrestamo, ServicioAplicacionObtenerPrestamo servicioAplicacionObtenerPrestamo) {
        this.servicioAplicacionGuardarPrestamo = servicioAplicacionGuardarPrestamo;
        this.servicioAplicacionObtenerPrestamo = servicioAplicacionObtenerPrestamo;
    }

    @PostMapping
    public DtoResultadoPrestar prestar(@RequestBody DtoSolicitudPrestarLibro dtoSolicitudPrestarLibro) {
        return servicioAplicacionGuardarPrestamo.ejecutar(dtoSolicitudPrestarLibro);
    }

    @GetMapping(value = "/{id-prestamo}")
    public DtoPrestamo obtenerPorId(@PathVariable("id-prestamo") int id) {
        return servicioAplicacionObtenerPrestamo.ejecutar(id);
    }

}

