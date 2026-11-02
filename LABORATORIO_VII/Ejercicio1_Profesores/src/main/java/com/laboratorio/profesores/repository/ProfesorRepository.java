package com.laboratorio.profesores.repository;

import com.laboratorio.profesores.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfesorRepository {

    private final List<Profesor> profesores = new ArrayList<>();
    private long siguienteId = 1;

    public synchronized Profesor guardar(Profesor profesor) {
        profesor.setId(siguienteId++);
        profesores.add(profesor);
        return profesor;
    }

    public synchronized List<Profesor> buscarTodos() {
        return new ArrayList<>(profesores);
    }

    public synchronized Optional<Profesor> buscarPorId(Long id) {
        return profesores.stream()
                .filter(profesor -> profesor.getId().equals(id))
                .findFirst();
    }

    public synchronized List<Profesor> buscarPorEspecialidad(String especialidad) {
        return profesores.stream()
                .filter(profesor -> especialidad.equalsIgnoreCase(profesor.getEspecialidad()))
                .toList();
    }
}
