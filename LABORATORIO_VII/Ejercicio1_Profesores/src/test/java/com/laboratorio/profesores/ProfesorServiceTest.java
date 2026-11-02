package com.laboratorio.profesores;

import com.laboratorio.profesores.exception.ProfesorNotFoundException;
import com.laboratorio.profesores.model.Profesor;
import com.laboratorio.profesores.repository.ProfesorRepository;
import com.laboratorio.profesores.service.ProfesorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfesorServiceTest {

    @Mock
    private ProfesorRepository repository;

    @InjectMocks
    private ProfesorService service;

    @Test
    void crearProfesorCorrectamente() {
        Profesor nuevo = new Profesor(null, "Ana", "López", "ana@correo.com", "Matemática", true);
        when(repository.guardar(any(Profesor.class))).thenAnswer(inv -> {
            Profesor p = inv.getArgument(0);
            p.setId(1L);
            return p;
        });

        Profesor creado = service.crear(nuevo);

        assertEquals(1L, creado.getId());
        assertEquals("Ana", creado.getNombre());
        assertTrue(creado.isEstado());
        verify(repository).guardar(nuevo);
    }

    @Test
    void obtenerProfesorExistente() {
        Profesor existente = new Profesor(1L, "Ana", "López", "ana@correo.com", "Matemática", true);
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(existente));

        Profesor resultado = service.obtenerPorId(1L);

        assertEquals("Ana", resultado.getNombre());
    }

    @Test
    void obtenerProfesorInexistenteLanzaExcepcion() {
        when(repository.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(ProfesorNotFoundException.class, () -> service.obtenerPorId(99L));
    }
}
