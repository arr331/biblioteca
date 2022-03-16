package com.ceiba.biblioteca.infraestructura.adaptador;

import com.ceiba.biblioteca.aplicacion.dto.DtoPrestamo;
import com.ceiba.biblioteca.aplicacion.dto.DtoResultadoPrestar;
import com.ceiba.biblioteca.dominio.modelo.Prestamo;
import com.ceiba.biblioteca.dominio.puerto.RepositorioPrestamo;
import com.ceiba.biblioteca.infraestructura.adaptador.crud.CrudRepositorioPrestamo;
import com.ceiba.biblioteca.infraestructura.adaptador.entidad.EntidadPrestamo;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioPrestamoImplementacion implements RepositorioPrestamo {
    private final CrudRepositorioPrestamo crudRepositorioPrestamo;

    public RepositorioPrestamoImplementacion(CrudRepositorioPrestamo crudRepositorioPrestamo) {
        this.crudRepositorioPrestamo = crudRepositorioPrestamo;
    }

    @Override
    public DtoResultadoPrestar prestar(Prestamo prestamo) {
        EntidadPrestamo entidadPrestamo = crudRepositorioPrestamo.save(
                new EntidadPrestamo(prestamo.getIsbn(), prestamo.getIdentificacionUsuario(), prestamo.getTipoUsuario(), prestamo.getFechaMaximaDevolucion()));
        return new DtoResultadoPrestar(entidadPrestamo.getId(), entidadPrestamo.getFechaMaximaDevolucion());
    }

    @Override
    public DtoPrestamo obtenerPorId(int id) {
        EntidadPrestamo entidadPrestamo = crudRepositorioPrestamo.findById(id).get();
        return new DtoPrestamo(entidadPrestamo.getId(), entidadPrestamo.getIsbn(),
                entidadPrestamo.getIdentificacionUsuario(), entidadPrestamo.getTipoUsuario(), entidadPrestamo.getFechaMaximaDevolucion());
    }

    @Override
    public boolean existePrestamo(int id) {
        return crudRepositorioPrestamo.existsById(id);
    }

    @Override
    public boolean existePrestamoPorIdentificacionUsuario(String identificacionUsuario) {
        return crudRepositorioPrestamo.existsByidentificacionUsuario(identificacionUsuario);
    }
}
