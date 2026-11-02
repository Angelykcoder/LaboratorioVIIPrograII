package com.laboratorio.empleados.controller;

import com.laboratorio.empleados.model.Empleado;
import com.laboratorio.empleados.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado crear(@Valid @RequestBody Empleado empleado) {
        return service.crear(empleado);
    }

    @GetMapping
    public List<Empleado> obtenerTodos(@RequestParam(required = false) String especialidad) {
        if (especialidad == null || especialidad.isBlank()) return service.obtenerTodos();
        return service.buscarPorEspecialidad(especialidad);
    }

    @GetMapping("/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}
