package com.cbtis.biblioteca.prestamos;

import com.cbtis.biblioteca.estudiante.Estudiante;
import com.cbtis.biblioteca.estudiante.EstudianteService;
import com.cbtis.biblioteca.libros.Libro;
import com.cbtis.biblioteca.libros.LibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final EstudianteService estudianteService;
    private final LibroService libroService;

    public PrestamoController(PrestamoService prestamoService, EstudianteService estudianteService, LibroService libroService) {
        this.prestamoService = prestamoService;
        this.estudianteService = estudianteService;
        this.libroService = libroService;
    }

    @GetMapping("/")
    public ResponseEntity<Map> findAll() {
        Map<String,Object> body = new HashMap<>();
        body.put("data", prestamoService.findAll());
        body.put("mensaje", "Prestamos encontrados");
        return ResponseEntity.ok(body);
    }

    @GetMapping("/estudiante/{numeroControl}")
    public ResponseEntity<Map> findByEstudianteId(@PathVariable String numeroControl) {
        Map<String,Object> body = new HashMap<>();
        Estudiante estudiante = estudianteService.findById(numeroControl);
        if (estudiante==null) {
            body.put("data", null);
            body.put("mensaje", "Estudiante no encontrado");
            return ResponseEntity.badRequest().body(body);
        }
        body.put("data", prestamoService.findByEstudianteNumeroControl(numeroControl));
        body.put("mensaje", "Prestamos encontrados");
        return ResponseEntity.ok(body);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Map> registrarPrestamo(@RequestBody PrestamoRequest prestamoRequest) {
        Map<String,Object> body = new HashMap<>();
        Estudiante estudiante = estudianteService.findById(prestamoRequest.getEstudiante());
        Libro libro = libroService.findById(prestamoRequest.getLibro());
        if (estudiante==null || libro==null) {
            body.put("data", null);
            body.put("error", "Estudiante o libro no encontrado");
            return ResponseEntity.badRequest().body(body);
        }
        Prestamo prestamo = prestamoService.registrarPrestamo(estudiante, libro, prestamoRequest.getFechaEntrega());
        if (prestamo==null) {
            body.put("data", null);
            body.put("mensaje", "El estudiante ya tiene un prestamo activo o no hay libros suficientes");
            return ResponseEntity.badRequest().body(body);
        }
        body.put("data", prestamo);
        body.put("mensaje", "Prestamo registrado");
        return ResponseEntity.ok(body);
    }

    @PutMapping("/terminar/{id}")
    public ResponseEntity<Map> terminarPrestamo(@PathVariable Integer id) {
        Map<String,Object> body = new HashMap<>();
        Prestamo prestamo = prestamoService.terminarPrestamo(id);
        if (prestamo==null) {
            body.put("data", null);
            body.put("mensaje", "Prestamo no encontrado");
            return ResponseEntity.badRequest().body(body);
        }
        body.put("data", prestamo);
        body.put("mensaje", "Prestamo terminado");
        return ResponseEntity.ok(body);
    }

}
