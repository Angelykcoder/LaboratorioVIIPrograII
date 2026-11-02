package com.laboratorio.proveedores.repository;

import com.laboratorio.proveedores.model.Proveedor;
import java.util.List;
import java.util.Optional;

public interface ProveedorRepository {
    Proveedor guardar(Proveedor proveedor);
    List<Proveedor> obtenerTodos();
    List<Proveedor> buscarPorCategoria(String categoria);
    Optional<Proveedor> buscarPorId(Long id);
}
