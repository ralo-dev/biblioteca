package com.cbtis.biblioteca.estudiante;

import org.springframework.stereotype.Service;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public Estudiante save(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Estudiante findById(String id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        estudianteRepository.deleteById(id);
    }

    public Estudiante update(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Iterable<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }
}
