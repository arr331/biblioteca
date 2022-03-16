package com.ceiba.biblioteca.dominio.servicio;

import com.ceiba.biblioteca.aplicacion.dto.DtoResultadoPrestar;
import com.ceiba.biblioteca.dominio.construirtest.DtoResultadoPrestarTestConstruir;
import com.ceiba.biblioteca.dominio.construirtest.PrestamoTestConstruir;
import com.ceiba.biblioteca.dominio.modelo.Prestamo;
import com.ceiba.biblioteca.dominio.puerto.RepositorioPrestamo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioGuardarPrestamoTest {

    @Test
    @DisplayName("Prestar exitosamente")
    void prestarExitoso() {
        // Arrange
        Prestamo prestamo = new PrestamoTestConstruir().construir();
        DtoResultadoPrestar resultadoPrestar = new DtoResultadoPrestarTestConstruir().construir();

        RepositorioPrestamo repositorio = Mockito.mock(RepositorioPrestamo.class);
        ServicioGuardarPrestamo servicio = new ServicioGuardarPrestamo(repositorio);

        Mockito.when(repositorio.prestar(Mockito.any(Prestamo.class))).thenReturn(resultadoPrestar);

        // Act
        int id = servicio.ejecutar(prestamo).getId();

        // Asserts
        Mockito.verify(repositorio, Mockito.times(1)).prestar(prestamo);
        Assertions.assertEquals(1, id);
    }

    @Test
    @DisplayName("Prestar exitosamente cuando usuario es invitado")
    void prestarExitosoCuandoUsuarioEsInvitado() {
        // Arrange
        Prestamo prestamo = new PrestamoTestConstruir().conTipoUsuario(3).construir();
        DtoResultadoPrestar resultadoPrestar = new DtoResultadoPrestarTestConstruir().construir();

        RepositorioPrestamo repositorio = Mockito.mock(RepositorioPrestamo.class);
        ServicioGuardarPrestamo servicio = new ServicioGuardarPrestamo(repositorio);

        Mockito.when(repositorio.prestar(Mockito.any(Prestamo.class))).thenReturn(resultadoPrestar);
        Mockito.when(repositorio.existePrestamoPorIdentificacionUsuario(Mockito.any())).thenReturn(false);

        // Act
        int id = servicio.ejecutar(prestamo).getId();

        // Asserts
        Mockito.verify(repositorio, Mockito.times(1)).prestar(prestamo);
        Assertions.assertEquals(1, id);
    }

    @Test
    @DisplayName("Validar excepción cuando un usuario invitado ya tiene un préstamo")
    void validarCuandoUsuarioInvitadoYaTieneUnPrestamo() {
        // Arrange
        Prestamo prestamo = new PrestamoTestConstruir().conTipoUsuario(3).construir();

        RepositorioPrestamo repositorio = Mockito.mock(RepositorioPrestamo.class);
        ServicioGuardarPrestamo servicio = new ServicioGuardarPrestamo(repositorio);

        Mockito.when(repositorio.existePrestamoPorIdentificacionUsuario(Mockito.any())).thenReturn(true);

        // Asserts y Act
        Assertions.assertEquals(
                String.format("El usuario con identificación %s ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo",
                        prestamo.getIdentificacionUsuario()),
                Assertions.assertThrows(RuntimeException.class, () -> servicio.ejecutar(prestamo)).getMessage());
    }

}