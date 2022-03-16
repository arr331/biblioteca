package com.ceiba.biblioteca.dominio.servicio;

import com.ceiba.biblioteca.aplicacion.dto.DtoResultadoPrestar;
import com.ceiba.biblioteca.dominio.modelo.Prestamo;
import com.ceiba.biblioteca.dominio.puerto.RepositorioPrestamo;
import com.ceiba.biblioteca.infraestructura.excepcion.ExcepcionMensaje;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ServicioGuardarPrestamo {
    private final RepositorioPrestamo repositorioPrestamo;

    private static final int USUARIO_INVITADO = 3;
    private static final String MENSAJE_EXISTE_PRESTAMO_INVITADO = "El usuario con identificación %s ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";

    public ServicioGuardarPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public DtoResultadoPrestar ejecutar(Prestamo prestamo) {
        if (prestamo.getTipoUsuario() == USUARIO_INVITADO) {
            verificarExistenciaPrestamoInvitado(prestamo.getIdentificacionUsuario());
        }
        return repositorioPrestamo.prestar(prestamo);
    }

    private void verificarExistenciaPrestamoInvitado(String identificacionUsuario) {
        if (repositorioPrestamo.existePrestamoPorIdentificacionUsuario(identificacionUsuario)) {
            throw new ExcepcionMensaje(HttpStatus.BAD_REQUEST, String.format(MENSAJE_EXISTE_PRESTAMO_INVITADO, identificacionUsuario));
        }
    }

}
