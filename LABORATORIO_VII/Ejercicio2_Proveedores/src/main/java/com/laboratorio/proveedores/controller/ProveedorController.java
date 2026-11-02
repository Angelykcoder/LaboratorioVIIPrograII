package com.laboratorio.proveedores.controller;

import com.laboratorio.proveedores.model.Proveedor;
import com.laboratorio.proveedores.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    private final ProveedorService service;

    public ProveedorController(ProveedorService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proveedor crear(@Valid @RequestBody Proveedor proveedor) {
        return service.crear(proveedor);
    }

    @GetMapping
    public List<Proveedor> obtenerTodos(@RequestParam(required = false) String categoria) {
        if (categoria == null || categoria.isBlank()) return service.obtenerTodos();
        return service.buscarPorCategoria(categoria);
    }

    @GetMapping("/{id}")
    public Proveedor obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}
