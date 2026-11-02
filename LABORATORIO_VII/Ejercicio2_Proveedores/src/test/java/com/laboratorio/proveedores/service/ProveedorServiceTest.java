package com.laboratorio.proveedores.service;

import com.laboratorio.proveedores.exception.ProveedorNoEncontradoException;
import com.laboratorio.proveedores.model.Proveedor;
import com.laboratorio.proveedores.repository.ProveedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProveedorServiceTest {
    @Mock
    private ProveedorRepository repository;
    private ProveedorService service;

    @BeforeEach
    void configurar() {
        service = new ProveedorService(repository);
    }

    @Test
    void creaProveedorCorrectamente() {
        Proveedor entrada = new Proveedor(null, "Ana", "López", "ana@example.com", "Limpieza", true);
        Proveedor guardado = new Proveedor(1L, "Ana", "López", "ana@example.com", "Limpieza", true);
        when(repository.guardar(entrada)).thenReturn(guardado);

        Proveedor resultado = service.crear(entrada);

        assertEquals(1L, resultado.getId());
        assertEquals("Ana", resultado.getNombre());
        verify(repository).guardar(entrada);
    }

    @Test
    void obtieneProveedorExistente() {
        Proveedor proveedor = new Proveedor(1L, "Ana", "López", "ana@example.com", "Limpieza", true);
        when(repository.buscarPorId(1L)).thenReturn(Optional.of(proveedor));

        assertSame(proveedor, service.obtenerPorId(1L));
    }

    @Test
    void obtenerProveedorInexistenteLanzaExcepcion() {
        when(repository.buscarPorId(99L)).thenReturn(Optional.empty());

        assertThrows(ProveedorNoEncontradoException.class, () -> service.obtenerPorId(99L));
    }
}
