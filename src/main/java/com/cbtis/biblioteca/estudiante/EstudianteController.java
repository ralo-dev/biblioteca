package com.cbtis.biblioteca.estudiante;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/")
    public Iterable<Estudiante> findAll() {
        return estudianteService.findAll();
    }

    @PostMapping("/")
    public Estudiante save(Estudiante estudiante) {
        return estudianteService.save(estudiante);
    }

    @GetMapping("/{id}")
    public Estudiante findById(@PathVariable Integer id) {
        return estudianteService.findById(id);
    }

    @PutMapping("/{id}")
    public Estudiante update(@PathVariable Integer id, Estudiante estudiante) {
        return estudianteService.update(estudiante);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        estudianteService.deleteById(id);
    }

}
