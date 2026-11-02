package com.laboratorio.proveedores.service;

import com.laboratorio.proveedores.exception.ProveedorNoEncontradoException;
import com.laboratorio.proveedores.model.Proveedor;
import com.laboratorio.proveedores.repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProveedorService {
    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public Proveedor crear(Proveedor proveedor) {
        proveedor.setId(null);
        return repository.guardar(proveedor);
    }

    public List<Proveedor> obtenerTodos() {
        return repository.obtenerTodos();
    }

    public List<Proveedor> buscarPorCategoria(String categoria) {
        return repository.buscarPorCategoria(categoria);
    }

    public Proveedor obtenerPorId(Long id) {
        return repository.buscarPorId(id).orElseThrow(() -> new ProveedorNoEncontradoException(id));
    }
}
