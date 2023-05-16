package com.cbtis.biblioteca.prestamos;

import com.cbtis.biblioteca.estudiante.Estudiante;
import com.cbtis.biblioteca.libros.Libro;
import com.cbtis.biblioteca.libros.LibroRepository;
import com.cbtis.biblioteca.libros.LibroService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroService libroService;

    public PrestamoService(PrestamoRepository prestamoRepository, LibroService libroService) {
        this.prestamoRepository = prestamoRepository;
        this.libroService = libroService;
    }

    public List<Prestamo> findByEstudianteNumeroControl (String numeroControl){
        return prestamoRepository.findAllByEstudianteNumeroControl(numeroControl);
    }

    public Prestamo registrarPrestamo(Estudiante estudiante, Libro libro, LocalDate fechaEntrega) {
        List<Prestamo> prestamosActivosEstudiante = prestamosActivos(estudiante);
        if (prestamosActivosEstudiante.size()!=0) {
            return null;
        }
        if (libro.getCantidad()==0) {
            return null;
        }
        libroService.prestarLibro(libro.getIsbn());
        Prestamo prestamo = new Prestamo();
        prestamo.setEstudiante(estudiante);
        prestamo.setLibro(libro);
        prestamo.setFechaPrestamo(java.time.LocalDate.now());
        prestamo.setFechaEntrega(fechaEntrega);
        prestamo.setEstadoPrestamo("ACTIVO");

        return prestamoRepository.save(prestamo);
    }

    public Prestamo terminarPrestamo(Integer idPrestamo){
        Prestamo prestamo = findById(idPrestamo);
        if (prestamo==null) {
            return null;
        }
        prestamo.setEstadoPrestamo("TERMINADO");
        libroService.devolverLibro(prestamo.getLibro().getIsbn());
        return prestamoRepository.save(prestamo);
    }

    public List<Prestamo> prestamosActivos (Estudiante estudiante){
        return prestamoRepository.findAllByEstudianteNumeroControlAndEstadoPrestamo(estudiante.getNumeroControl(), "ACTIVO");
    }

    public Prestamo save(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public Prestamo findById(Integer id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        prestamoRepository.deleteById(id);
    }

    public Prestamo update(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    public Iterable<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

}
