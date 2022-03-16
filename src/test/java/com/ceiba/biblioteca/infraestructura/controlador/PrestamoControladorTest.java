package com.ceiba.biblioteca.infraestructura.controlador;

import com.ceiba.biblioteca.aplicacion.dto.DtoPrestamo;
import com.ceiba.biblioteca.aplicacion.dto.DtoResultadoPrestar;
import com.ceiba.biblioteca.aplicacion.dto.DtoSolicitudPrestarLibro;
import com.ceiba.biblioteca.dominio.puerto.RepositorioPrestamo;
import com.ceiba.biblioteca.infraestructura.ApplicationMock;
import com.ceiba.biblioteca.infraestructura.construirtest.DtoSolicitarPrestarTestConstruir;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = ApplicationMock.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PrestamoControladorTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Autowired
    RepositorioPrestamo repositorioPrestamo;

    @Test
    @DisplayName("Debe obtener el préstamo después de crearlo")
    void obtenerPrestamo() throws Exception {

        DtoSolicitudPrestarLibro dto = new DtoSolicitarPrestarTestConstruir().construir();

        DtoResultadoPrestar respuesta = prestar(dto);

        mocMvc.perform(get("/prestamo/" + respuesta.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn", is("DASD154212")))
                .andExpect(jsonPath("$.identificacionUsuario", is("154515485")));
    }

    private DtoResultadoPrestar prestar(DtoSolicitudPrestarLibro dtoSolicitud) throws Exception {
        MvcResult result = mocMvc.perform(MockMvcRequestBuilders.post("/prestamo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoSolicitud))
                )
                .andExpect(status().isOk())
                .andReturn();

        DtoResultadoPrestar respuesta = objectMapper.readValue(result.getResponse().getContentAsString(), DtoResultadoPrestar.class);

        Assertions.assertNotNull(respuesta.getId());

        DtoPrestamo dtoPrestamo = repositorioPrestamo.obtenerPorId(respuesta.getId());

        Assertions.assertEquals(dtoSolicitud.getIsbn(), dtoPrestamo.getIsbn());
        Assertions.assertEquals(dtoSolicitud.getIdentificacionUsuario(), dtoPrestamo.getIdentificacionUsuario());

        return respuesta;
    }

}