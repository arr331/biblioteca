package com.ceiba.biblioteca.infraestructura.adaptador.crud;

import com.ceiba.biblioteca.infraestructura.adaptador.entidad.EntidadPrestamo;
import org.springframework.data.repository.CrudRepository;

public interface CrudRepositorioPrestamo extends CrudRepository<EntidadPrestamo, Integer> {
    boolean existsByidentificacionUsuario(String identificacionUsuario);
}
