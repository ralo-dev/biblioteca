package com.cbtis.biblioteca.libros;

import org.springframework.stereotype.Service;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public Libro create(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro update(Libro libro) {
        return libroRepository.save(libro);
    }

    public void delete(Long id) {
        libroRepository.deleteById(id);
    }

    public Libro findById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Iterable<Libro> findAll() {
        return libroRepository.findAll();
    }

    public Iterable<Libro> findByTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Iterable<Libro> findByAutor(String autor) {
        return libroRepository.findByAutorContainingIgnoreCase(autor.toLowerCase());
    }

    public Iterable<Libro> findByIsbn(String isbn) {
        return libroRepository.findByIsbnContainingIgnoreCase(isbn);
    }
}
