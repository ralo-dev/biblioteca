package com.cbtis.biblioteca.prestamos;

import com.cbtis.biblioteca.estudiante.Estudiante;
import com.cbtis.biblioteca.estudiante.EstudianteService;
import com.cbtis.biblioteca.libros.Libro;
import com.cbtis.biblioteca.libros.LibroService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestamos")
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
    public Iterable<Prestamo> findAll() {
        return prestamoService.findAll();
    }

    @GetMapping("/estudiante/{numeroControl}")
    public Iterable<Prestamo> findByEstudianteId(@PathVariable int numeroControl) {
        return prestamoService.findByEstudianteNumeroControl(numeroControl);
    }

    @PostMapping("/")
    public Prestamo registrarPrestamo(@RequestBody PrestamoRequest prestamoRequest) {
        Prestamo nuevoPrestamo = new Prestamo();
        Estudiante estudiante = estudianteService.findById(prestamoRequest.getEstudiante());
        Libro libro = libroService.findById(prestamoRequest.getLibro());
        if (estudiante==null || libro==null) {
            return null;
        }
        return prestamoService.registrarPrestamo(estudiante, libro, prestamoRequest.getFechaEntrega());
    }

    @PutMapping("/terminar/{id}")
    public Prestamo terminarPrestamo(@PathVariable Integer id) {
        return prestamoService.terminarPrestamo(id);
    }

}
