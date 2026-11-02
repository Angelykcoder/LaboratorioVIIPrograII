package com.laboratorio.proveedores;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "app.api-key=clave-de-prueba")
class ProveedorApiTest {

    private static final String RUTA = "/api/proveedores";
    private static final String HEADER = "X-API-KEY";
    private static final String CLAVE = "clave-de-prueba";
    private static final String JSON_VALIDO =
            "{\"nombre\":\"Ana\",\"apellido\":\"García\",\"correo\":\"a@example.com\",\"categoria\":\"Limpieza\"}";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sinApiKeyDevuelve401() throws Exception {
        mockMvc.perform(get(RUTA)).andExpect(status().isUnauthorized());
    }

    @Test
    void conApiKeyIncorrectaDevuelve401() throws Exception {
        mockMvc.perform(get(RUTA).header(HEADER, "incorrecta")).andExpect(status().isUnauthorized());
    }

    @Test
    void crearDevuelve201ConEstadoPorDefecto() throws Exception {
        mockMvc.perform(post(RUTA).header(HEADER, CLAVE)
                        .contentType(MediaType.APPLICATION_JSON).content(JSON_VALIDO))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado").value(true));
    }

    @Test
    void nombreVacioYCorreoInvalidoDevuelven400() throws Exception {
        String invalido = "{\"nombre\":\"\",\"apellido\":\"García\",\"correo\":\"no-es-correo\",\"categoria\":\"Limpieza\"}";
        mockMvc.perform(post(RUTA).header(HEADER, CLAVE)
                        .contentType(MediaType.APPLICATION_JSON).content(invalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errores.nombre").exists())
                .andExpect(jsonPath("$.errores.correo").exists());
    }

    @Test
    void idInexistenteDevuelve404() throws Exception {
        mockMvc.perform(get(RUTA + "/9999").header(HEADER, CLAVE)).andExpect(status().isNotFound());
    }

    @Test
    void filtraPorValorSinDistinguirMayusculas() throws Exception {
        mockMvc.perform(post(RUTA).header(HEADER, CLAVE)
                .contentType(MediaType.APPLICATION_JSON).content(JSON_VALIDO));

        mockMvc.perform(get(RUTA).param("categoria", "Limpieza").header(HEADER, CLAVE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoria").value("Limpieza"));
        mockMvc.perform(get(RUTA).param("categoria", "valor-que-no-existe").header(HEADER, CLAVE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
