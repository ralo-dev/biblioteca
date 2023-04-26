package com.cbtis.biblioteca.libros;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/")
    public String index() {
        return "Bienvenido a la API de libros. Para ver todos los libros, visita /api/libros/all, para buscar por título, visita /api/libros/titulo/{titulo}, para buscar por autor, visita /api/libros/autor/{autor}, para buscar por ISBN, visita /api/libros/isbn/{isbn}. Para crear un libro, visita /api/libros/create, para actualizar un libro, visita /api/libros/update, para eliminar un libro, visita /api/libros/delete/{id}.";
    }

    @GetMapping("/all")
    public Iterable<Libro> findAll() {
        return libroService.findAll();
    }

    @GetMapping("/titulo/{titulo}")
    public Iterable<Libro> findByTitulo(@PathVariable String titulo) {
        return libroService.findByTitulo(titulo);
    }

    @GetMapping("/autor/{autor}")
    public Iterable<Libro> findByAutor(@PathVariable String autor) {
        return libroService.findByAutor(autor);
    }

    @GetMapping("/isbn/{isbn}")
    public Iterable<Libro> findByIsbn(@PathVariable String isbn) {
        return libroService.findByIsbn(isbn);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        libroService.delete(id);
    }

    @PostMapping("/create")
    public Libro create(@RequestBody Libro libro) {
        return libroService.create(libro);
    }

    @PutMapping("/update")
    public Libro update(@RequestBody Libro libro) {
        return libroService.update(libro);
    }

}
