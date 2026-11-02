package com.laboratorio.empleados.service;

import com.laboratorio.empleados.exception.EmpleadoNoEncontradoException;
import com.laboratorio.empleados.model.Empleado;
import com.laboratorio.empleados.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpleadoService {
    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado crear(Empleado empleado) {
        empleado.setId(null);
        return repository.guardar(empleado);
    }

    public List<Empleado> obtenerTodos() {
        return repository.obtenerTodos();
    }

    public List<Empleado> buscarPorEspecialidad(String especialidad) {
        return repository.buscarPorEspecialidad(especialidad);
    }

    public Empleado obtenerPorId(Long id) {
        return repository.buscarPorId(id).orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }
}
