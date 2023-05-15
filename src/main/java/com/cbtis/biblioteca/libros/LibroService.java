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

    public void delete(String id) {
        libroRepository.deleteById(id);
    }

    public Libro findById(String id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Iterable<Libro> findAll() {
        return libroRepository.findAll();
    }

    public Libro prestarLibro (String isbn){
        Libro libro = libroRepository.findById(isbn).orElse(null);
        if (libro==null) {
            return null;
        }
        if (libro.getCantidad()==0) {
            return null;
        }
        libro.setCantidad(libro.getCantidad()-1);
        return libroRepository.save(libro);
    }

    public Libro devolverLibro (String isbn){
        Libro libro = libroRepository.findById(isbn).orElse(null);
        if (libro==null) {
            return null;
        }
        libro.setCantidad(libro.getCantidad()+1);
        return libroRepository.save(libro);
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
