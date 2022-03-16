package com.ceiba.biblioteca.dominio.servicio;

import com.ceiba.biblioteca.aplicacion.dto.DtoPrestamo;
import com.ceiba.biblioteca.dominio.construirtest.PrestamoTestConstruir;
import com.ceiba.biblioteca.dominio.puerto.RepositorioPrestamo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioObtenerPrestamoTest {

    @Test
    @DisplayName("Obtener préstamo exitosamente")
    void obtenerExitoso() {
        // Arrange
        DtoPrestamo dtoPrestamo = new PrestamoTestConstruir().construirDto();

        RepositorioPrestamo repositorio = Mockito.mock(RepositorioPrestamo.class);
        ServicioObtenerPrestamo servicio = new ServicioObtenerPrestamo(repositorio);

        Mockito.when(repositorio.existePrestamo(Mockito.any(Integer.class))).thenReturn(true);
        Mockito.when(repositorio.obtenerPorId(Mockito.any(Integer.class))).thenReturn(dtoPrestamo);

        // Act
        int id = servicio.ejecutar(2).getId();

        // Asserts
        Mockito.verify(repositorio, Mockito.times(1)).obtenerPorId(2);
        Mockito.verify(repositorio, Mockito.times(1)).existePrestamo(2);
        Assertions.assertEquals(dtoPrestamo.getId(), id);
    }

    @Test
    @DisplayName("Validar excepción cuando préstamo no existe")
    void validarCuandoElPrestamoNoExiste() {
        // Arrange
        RepositorioPrestamo repositorio = Mockito.mock(RepositorioPrestamo.class);
        ServicioObtenerPrestamo servicio = new ServicioObtenerPrestamo(repositorio);

        Mockito.when(repositorio.existePrestamo(Mockito.any(Integer.class))).thenReturn(false);

        // Act (servicio.ejecutar) and Assert
        Assertions.assertEquals("No existe un préstamo con este criterio de búsqueda.",
                Assertions.assertThrows(RuntimeException.class, () -> servicio.ejecutar(1)).getMessage());
    }

}