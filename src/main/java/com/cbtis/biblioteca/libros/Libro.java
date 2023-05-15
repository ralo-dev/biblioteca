package com.cbtis.biblioteca.libros;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "libros")
@Getter
@Setter
@NoArgsConstructor
public class Libro {

    @Id
    @Column(name="ISBN")
    private String isbn;
    private String titulo;
    private String autor;
    private String editorial;
    private String edicion;
    @Column(name="anio_publicacion")
    private int anioPublicacion;
    private String descripcion;
    private int cantidad;

    public Libro(String titulo, String autor, String editorial, String isbn, String edicion, int anioPublicacion, String descripcion, int cantidad) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.edicion = edicion;
        this.anioPublicacion = anioPublicacion;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

}
