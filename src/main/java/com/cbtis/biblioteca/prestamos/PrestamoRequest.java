package com.cbtis.biblioteca.prestamos;

import com.cbtis.biblioteca.estudiante.Estudiante;
import com.cbtis.biblioteca.libros.Libro;
import com.cbtis.biblioteca.usuarios.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PrestamoRequest {

    private LocalDate fechaEntrega;

    private String libro;

    private String estudiante;
}
