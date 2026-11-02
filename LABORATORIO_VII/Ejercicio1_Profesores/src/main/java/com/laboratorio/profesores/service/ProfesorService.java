package com.laboratorio.profesores.service;

import com.laboratorio.profesores.exception.ProfesorNotFoundException;
import com.laboratorio.profesores.model.Profesor;
import com.laboratorio.profesores.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private static final String ESTADO_POR_DEFECTO = "ACTIVO";

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public Profesor crear(Profesor profesor) {
        if (profesor.getEstado() == null || profesor.getEstado().isBlank()) {
            profesor.setEstado(ESTADO_POR_DEFECTO);
        }
        return repository.guardar(profesor);
    }

    public List<Profesor> listar(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            return repository.buscarTodos();
        }
        return repository.buscarPorEspecialidad(especialidad);
    }

    public Profesor obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ProfesorNotFoundException(id));
    }
}
