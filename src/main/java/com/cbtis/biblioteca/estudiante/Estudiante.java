package com.cbtis.biblioteca.estudiante;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "estudiantes")
@Getter
@Setter
@NoArgsConstructor
public class Estudiante {

    @Id
    @Column(name="numero_control")
    private String numeroControl;

    @Column(name="nombre_estudiante")
    private String nombreEstudiante;

    @Column(name="grado_estudiante")
    private int gradoEstudiante;

    @Column(name="grupo_estudiante")
    private String grupoEstudiante;

    @Column(name="correo_estudiante")
    private String correoEstudiante;

    @Column(name="telefono_estudiante")
    private String telefonoEstudiante;
}
