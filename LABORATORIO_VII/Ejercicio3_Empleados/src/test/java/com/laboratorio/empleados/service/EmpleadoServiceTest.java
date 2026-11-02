package com.laboratorio.empleados.service;

import com.laboratorio.empleados.exception.EmpleadoNoEncontradoException;
import com.laboratorio.empleados.model.Empleado;
import com.laboratorio.empleados.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {
    @Mock
    private EmpleadoRepository repository;
    private EmpleadoService service;

    @BeforeEach
    void configurar() {
        service = new EmpleadoService(repository);
    }

    @Test
    void creaEmpleadoCorrectamente() {
        Empleado entrada = new Empleado(null, "Carlos", "García", "carlos@example.com", "Sistemas", true);
        Empleado guardado = new Empleado(1L, "Carlos", "García", "carlos@example.com", "Sistemas", true);
        when(repository.guardar(entrada)).thenReturn(guardado);

        Empleado resultado = service.crear(entrada);

        assertEquals(1L, resultado.getId());
        assertEquals("Carlos", resultado.getNombre());
        verify(repository).guardar(entrada);
    }

    @Test
    void obtieneEmpleadoExistente() {
        Empleado empleado = new Empleado(1L, "Carlos", "García", "carlos@example.com", "Sistemas", true);
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(empleado));

        assertSame(empleado, service.obtenerPorId(1L));
    }

    @Test
    void obtenerEmpleadoInexistenteLanzaExcepcion() {
        when(repository.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(EmpleadoNoEncontradoException.class, () -> service.obtenerPorId(99L));
    }
}
