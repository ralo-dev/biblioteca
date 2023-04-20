package com.cbtis.biblioteca.libros;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "libros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String editorial;
    private String isbn;
    private String edicion;
    private int anio;
    private String descripcion;

    public Libro(String titulo, String autor, String editorial, String isbn, String edicion, int anio, String descripcion) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.edicion = edicion;
        this.anio = anio;
        this.descripcion = descripcion;
    }

}
