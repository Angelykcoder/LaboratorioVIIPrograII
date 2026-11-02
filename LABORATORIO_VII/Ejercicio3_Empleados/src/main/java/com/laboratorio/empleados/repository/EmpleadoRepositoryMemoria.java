package com.laboratorio.empleados.repository;

import com.laboratorio.empleados.model.Empleado;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmpleadoRepositoryMemoria implements EmpleadoRepository {
    private final List<Empleado> empleados = new ArrayList<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    @Override
    public synchronized Empleado guardar(Empleado empleado) {
        empleado.setId(siguienteId.getAndIncrement());
        empleados.add(empleado);
        return empleado;
    }

    @Override
    public synchronized List<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    @Override
    public synchronized List<Empleado> buscarPorEspecialidad(String especialidad) {
        return empleados.stream()
                .filter(empleado -> empleado.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    @Override
    public synchronized Optional<Empleado> buscarPorId(Long id) {
        return empleados.stream().filter(empleado -> empleado.getId().equals(id)).findFirst();
    }
}
