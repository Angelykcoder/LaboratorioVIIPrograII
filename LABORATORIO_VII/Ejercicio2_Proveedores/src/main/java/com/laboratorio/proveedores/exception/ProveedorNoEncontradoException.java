package com.laboratorio.proveedores.exception;

public class ProveedorNoEncontradoException extends RuntimeException {
    public ProveedorNoEncontradoException(Long id) {
        super("No se encontró el proveedor con id " + id);
    }
}
