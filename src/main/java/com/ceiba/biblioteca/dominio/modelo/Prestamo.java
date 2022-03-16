package com.ceiba.biblioteca.dominio.modelo;

import com.ceiba.biblioteca.infraestructura.excepcion.ExcepcionMensaje;
import org.springframework.http.HttpStatus;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Prestamo {
    private int id;
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
    private String fechaMaximaDevolucion;

    private static final int USUARIO_AFILIADO = 1;
    private static final int USUARIO_EMPLEADO = 2;
    private static final int USUARIO_INVITADO = 3;

    private static final int DIAS_AFILIADO = 10;
    private static final int DIAS_EMPLEADO = 8;
    private static final int DIAS_INVITADO = 7;

    private static final int TAMANO_MAXIMO_ISBN = 10;
    private static final int TAMANO_MAXIMO_IDENTIFICACION_USUARIO = 10;
    private static final String CAMPO_ISBN = "ISBN";
    private static final String CAMPO_IDENTIFICACION_USUARIO = "identificación del usuario";

    private static final String USUARIO_NO_PERMITIDO = "Tipo de usuario no permitido en la biblioteca";
    private static final String CAMPO_ISBN_OBLIGATORIO = "El campo ISBN es un campo obligatorio";
    private static final String CAMPO_IDENTIFICACION_USUARIO_OBLIGATORIO = "El campo identificación del usuario es un campo obligatorio";
    private static final String FORMATO_FECHA_MAXIMA_DEVOLUCION = "dd/MM/yyyy";
    private static final String MENSAJE_TAMANO_MAXIMO = "El tamaño máximo del campo %s es %s";

    public static Prestamo of(String isbn, String identificacionUsuario, int tipoUsuario) {
        verificarCampoObligatorio(isbn, CAMPO_ISBN_OBLIGATORIO);
        verificarTamanoMaximoCampo(isbn, TAMANO_MAXIMO_ISBN, CAMPO_ISBN);
        verificarCampoObligatorio(identificacionUsuario, CAMPO_IDENTIFICACION_USUARIO_OBLIGATORIO);
        verificarTamanoMaximoCampo(identificacionUsuario, TAMANO_MAXIMO_IDENTIFICACION_USUARIO, CAMPO_IDENTIFICACION_USUARIO);
        verificarUsuario(tipoUsuario);
        String fechaMaximaDevolucion = obtenerFechaMaximaDevolucion(tipoUsuario);

        return new Prestamo(isbn, identificacionUsuario, tipoUsuario, fechaMaximaDevolucion);
    }

    private Prestamo(String isbn, String identificacionUsuario, int tipoUsuario, String fechaMaximaDevolucion) {
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    private static void verificarCampoObligatorio(String value, String mensajeError) {
        if (value == null || value.trim().isEmpty()) {
            throw new ExcepcionMensaje(HttpStatus.BAD_REQUEST, mensajeError);
        }
    }

    private static void verificarUsuario(int tipoUsuario) {
        if (tipoUsuario != USUARIO_AFILIADO && tipoUsuario != USUARIO_EMPLEADO  && tipoUsuario != USUARIO_INVITADO) {
            throw new ExcepcionMensaje(HttpStatus.BAD_REQUEST, USUARIO_NO_PERMITIDO);
        }
    }

    private static String obtenerFechaMaximaDevolucion(int tipoUsuario) {
        switch (tipoUsuario) {
            case USUARIO_AFILIADO:
                return sumarDiasSinSabadoYDomingo(DIAS_AFILIADO);
            case USUARIO_EMPLEADO:
                return sumarDiasSinSabadoYDomingo(DIAS_EMPLEADO);
            case USUARIO_INVITADO:
                return sumarDiasSinSabadoYDomingo(DIAS_INVITADO);
            default:
                throw new ExcepcionMensaje(HttpStatus.BAD_REQUEST, USUARIO_NO_PERMITIDO);
        }
    }

    private static String sumarDiasSinSabadoYDomingo(int diasASumar) {
        LocalDate fecha = LocalDate.now();
        int totalDias = 0;
        while (totalDias < diasASumar) {
            fecha = fecha.plusDays(1);
            if (!(fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++totalDias;
            }
        }
        return fecha.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_MAXIMA_DEVOLUCION));
    }

    private static void verificarTamanoMaximoCampo(String value, int tamanoMaximo, String campo) {
        if (value.length() > tamanoMaximo) {
            throw new ExcepcionMensaje(HttpStatus.BAD_REQUEST, String.format(MENSAJE_TAMANO_MAXIMO, campo, tamanoMaximo));
        }
    }

    public int getId() { return id; }

    public String getIsbn() { return isbn; }

    public String getIdentificacionUsuario() { return identificacionUsuario; }

    public int getTipoUsuario() { return tipoUsuario; }

    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }
}
