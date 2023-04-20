package com.cbtis.biblioteca.libros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    Iterable<Libro> findByTituloContainingIgnoreCase(String nombre);
    Iterable<Libro> findByAutorContainingIgnoreCase(String autor);

}
