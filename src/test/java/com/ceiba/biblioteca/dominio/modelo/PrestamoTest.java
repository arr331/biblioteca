package com.ceiba.biblioteca.dominio.modelo;

import com.ceiba.biblioteca.dominio.construirtest.PrestamoTestConstruir;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoTest {

    @Test
    @DisplayName("Validar creación del préstamo")
    void validarCreacion() {
        Prestamo prestamo = new PrestamoTestConstruir().construir();
        assertEquals("DASD154212", prestamo.getIsbn());
        assertEquals("154515485", prestamo.getIdentificacionUsuario());
        assertEquals(1, prestamo.getTipoUsuario());
    }

    @Test
    @DisplayName("Validar que el Isbn sea obligatorio")
    void validarIsbnObligatorio() {
        PrestamoTestConstruir prestamoTestConstruir = new PrestamoTestConstruir().conIsbn(null);
        Assertions.assertEquals("El campo ISBN es un campo obligatorio",
                Assertions.assertThrows(RuntimeException.class, () -> prestamoTestConstruir.construir()).getMessage());
    }

    @Test
    @DisplayName("Validar el tamaño maximo del Isbn")
    void validarIsbnTamañoMaximo() {
        PrestamoTestConstruir prestamoTestConstruir = new PrestamoTestConstruir().conIsbn("ABCDEFGHIJK");
        Assertions.assertEquals("El tamaño máximo del campo ISBN es 10",
                Assertions.assertThrows(RuntimeException.class, () -> prestamoTestConstruir.construir()).getMessage());
    }

    @Test
    @DisplayName("Validar que la identificacion sea obligatoria")
    void validarIdentificacionObligatoria() {
        PrestamoTestConstruir prestamoTestConstruir = new PrestamoTestConstruir().conIdentificacionUsuario(null);
        Assertions.assertEquals("El campo identificación del usuario es un campo obligatorio",
                Assertions.assertThrows(RuntimeException.class, () -> prestamoTestConstruir.construir()).getMessage());
    }

    @Test
    @DisplayName("Validar que la identificacion sea del tamaño apropiado")
    void validarIdentificacionTamañoApropiado() {
        PrestamoTestConstruir prestamoTestConstruir = new PrestamoTestConstruir().conIdentificacionUsuario("100845654822");
        Assertions.assertEquals("El tamaño máximo del campo identificación del usuario es 10",
                Assertions.assertThrows(RuntimeException.class, () -> prestamoTestConstruir.construir()).getMessage());
    }

    @Test
    @DisplayName("Validar si el usuario es desconocido")
    void validarUsuarioDesconocido() {
        PrestamoTestConstruir prestamoTestConstruir = new PrestamoTestConstruir().conTipoUsuario(6);
        Assertions.assertEquals("Tipo de usuario no permitido en la biblioteca",
                Assertions.assertThrows(RuntimeException.class, () -> prestamoTestConstruir.construir()).getMessage());
    }

    @Test
    @DisplayName("Validar los días del préstamo de un usuario tipo afiliado")
    void validarDiasUsuarioAfiliado() {
        Prestamo prestamo = new PrestamoTestConstruir().conTipoUsuario(1).construir();

        LocalDate fechaPrestamo = addDaysSkippingWeekends(LocalDate.now(), 10);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(fechaPrestamo.format(formato), prestamo.getFechaMaximaDevolucion());
    }

    @Test
    @DisplayName("Validar los días del préstamo de un usuario tipo empleado")
    void validarDiasUsuarioEmpleado() {
        Prestamo prestamo = new PrestamoTestConstruir().conTipoUsuario(2).construir();

        LocalDate fechaPrestamo = addDaysSkippingWeekends(LocalDate.now(), 8);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(fechaPrestamo.format(formato), prestamo.getFechaMaximaDevolucion());
    }

    @Test
    @DisplayName("Validar los días del préstamo de un usuario tipo invitado")
    void validarDiasUsuarioInvitado() {
        Prestamo prestamo = new PrestamoTestConstruir().conTipoUsuario(3).construir();

        LocalDate fechaPrestamo = addDaysSkippingWeekends(LocalDate.now(), 7);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(fechaPrestamo.format(formato), prestamo.getFechaMaximaDevolucion());
    }

    private LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
}