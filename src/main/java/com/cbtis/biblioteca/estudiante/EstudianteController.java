package com.cbtis.biblioteca.estudiante;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping("/")
    public Iterable<Estudiante> findAll() {
        return estudianteService.findAll();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Map> save(@RequestBody Estudiante estudiante) {
        Map<String,Object> body = new HashMap<>();
        body.put("data", estudianteService.save(estudiante));
        body.put("mensaje", "Estudiante registrado");
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> findById(@PathVariable String id) {
        Map<String,Object> body = new HashMap<>();
        Estudiante estudiante = estudianteService.findById(id);
        if (estudiante==null) {
            body.put("data", null);
            body.put("mensaje", "Estudiante no encontrado");
            return ResponseEntity.badRequest().body(body);
        }
        body.put("data", estudiante);
        body.put("mensaje", "Estudiante encontrado");
        return ResponseEntity.ok(body);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Map> update(@PathVariable String id, @RequestBody Estudiante estudiante) {
        Map<String,Object> body = new HashMap<>();
        Estudiante estudianteActual = estudianteService.findById(id);
        if (estudianteActual==null) {
            body.put("data", null);
            body.put("mensaje", "Estudiante no encontrado");
            return ResponseEntity.badRequest().body(body);
        }
        body.put("data", estudianteService.update(estudiante));
        body.put("mensaje", "Estudiante actualizado");
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map> deleteById(@PathVariable String id) {
        Map<String,Object> body = new HashMap<>();
        Estudiante estudiante = estudianteService.findById(id);
        if (estudiante==null) {
            body.put("data", null);
            body.put("mensaje", "Estudiante no encontrado");
            return ResponseEntity.badRequest().body(body);
        }
        body.put("data", null);
        body.put("mensaje", "Estudiante eliminado");
        estudianteService.deleteById(id);
        return ResponseEntity.ok(body);
    }

}
