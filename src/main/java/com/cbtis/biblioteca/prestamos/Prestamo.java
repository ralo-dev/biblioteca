package com.cbtis.biblioteca.prestamos;

import com.cbtis.biblioteca.estudiante.Estudiante;
import com.cbtis.biblioteca.libros.Libro;
import com.cbtis.biblioteca.usuarios.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Getter
@Setter
@NoArgsConstructor
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prestamo")
    private Integer id;

    @Column(name="fecha_prestamo")
    private LocalDate fechaPrestamo;

    @Column(name="fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name="estado_prestamo")
    private String estadoPrestamo;

    @ManyToOne
    @JoinColumn(name = "numero_control")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
