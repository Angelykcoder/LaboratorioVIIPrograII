package com.laboratorio.empleados.repository;

import com.laboratorio.empleados.model.Empleado;
import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository {
    Empleado guardar(Empleado empleado);
    List<Empleado> obtenerTodos();
    List<Empleado> buscarPorEspecialidad(String especialidad);
    Optional<Empleado> buscarPorId(Long id);
}
