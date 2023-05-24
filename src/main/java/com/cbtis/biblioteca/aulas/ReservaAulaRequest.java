package com.cbtis.biblioteca.aulas;

import lombok.Data;

@Data
public class ReservaAulaRequest {
    private String nombreProfesor;
    private String fechaReserva;
    private String horaInicio;
    private String horaFin;
    private String grupo;
    private String materia;
}
