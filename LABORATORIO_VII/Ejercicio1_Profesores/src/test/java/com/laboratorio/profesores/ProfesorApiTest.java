package com.laboratorio.profesores;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "app.api-key=clave-de-prueba")
class ProfesorApiTest {

    private static final String HEADER = "X-API-KEY";
    private static final String CLAVE = "clave-de-prueba";
    private static final String JSON_VALIDO =
            "{\"nombre\":\"Ana\",\"apellido\":\"López\",\"correo\":\"ana@correo.com\",\"especialidad\":\"Matemática\"}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sinApiKeyDevuelve401() throws Exception {
        mockMvc.perform(get("/api/profesores")).andExpect(status().isUnauthorized());
    }

    @Test
    void conApiKeyIncorrectaDevuelve401() throws Exception {
        mockMvc.perform(get("/api/profesores").header(HEADER, "incorrecta"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void crearProfesorDevuelve201() throws Exception {
        mockMvc.perform(post("/api/profesores").header(HEADER, CLAVE)
                        .contentType(MediaType.APPLICATION_JSON).content(JSON_VALIDO))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado").value("ACTIVO"));
    }

    @Test
    void nombreVacioYCorreoInvalidoDevuelven400() throws Exception {
        String invalido = "{\"nombre\":\"\",\"apellido\":\"López\",\"correo\":\"no-es-correo\"}";
        mockMvc.perform(post("/api/profesores").header(HEADER, CLAVE)
                        .contentType(MediaType.APPLICATION_JSON).content(invalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errores.nombre").exists())
                .andExpect(jsonPath("$.errores.correo").exists());
    }

    @Test
    void idInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get("/api/profesores/9999").header(HEADER, CLAVE))
                .andExpect(status().isNotFound());
    }
}
