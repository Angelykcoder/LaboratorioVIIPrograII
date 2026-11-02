package com.laboratorio.proveedores.repository;

import com.laboratorio.proveedores.model.Proveedor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProveedorRepositoryMemoria implements ProveedorRepository {
    private final List<Proveedor> proveedores = new ArrayList<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    @Override
    public synchronized Proveedor guardar(Proveedor proveedor) {
        proveedor.setId(siguienteId.getAndIncrement());
        proveedores.add(proveedor);
        return proveedor;
    }

    @Override
    public synchronized List<Proveedor> obtenerTodos() {
        return new ArrayList<>(proveedores);
    }

    @Override
    public synchronized List<Proveedor> buscarPorCategoria(String categoria) {
        return proveedores.stream()
                .filter(proveedor -> proveedor.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    @Override
    public synchronized Optional<Proveedor> buscarPorId(Long id) {
        return proveedores.stream().filter(proveedor -> proveedor.getId().equals(id)).findFirst();
    }
}
