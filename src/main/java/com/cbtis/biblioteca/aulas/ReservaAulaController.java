package com.cbtis.biblioteca.aulas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "*")
public class ReservaAulaController {
    private final ReservaAulaService reservaAulaService;

    public ReservaAulaController(ReservaAulaService reservaAulaService) {
        this.reservaAulaService = reservaAulaService;
    }

    @PostMapping
    public ResponseEntity<Object> reservarAula(@RequestBody ReservaAulaRequest reservaAula) {
        LocalDate fecha = LocalDate.parse(reservaAula.getFechaReserva());
        LocalTime inicio = LocalTime.parse(reservaAula.getHoraInicio());
        LocalTime fin = LocalTime.parse(reservaAula.getHoraFin());
        String nombreProfesor = reservaAula.getNombreProfesor();
        String grupo = reservaAula.getGrupo();
        String materia = reservaAula.getMateria();
        ReservaAula reserva = reservaAulaService.reservarAula(nombreProfesor, fecha, inicio, fin, grupo, materia);
        if (reserva != null) {
            return ResponseEntity.ok(reserva);
        } else {
            String body = String.format("El aula no está disponible para la fecha %s y el intervalo de tiempo [%s, %s], el aula está disponible de 7:00 a 16:00 de lunes a viernes", reservaAula.getFechaReserva(), reservaAula.getHoraInicio(), reservaAula.getHoraFin());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<List<DisponibilidadAula>> consultarHorasDisponiblesAula() {
        List<DisponibilidadAula> disponibilidadAulaList = reservaAulaService.consultarHorasDisponibles();
        return ResponseEntity.ok(disponibilidadAulaList);
    }


    @GetMapping("/")
    public List<ReservaAula> obtenerReservasAula() {
        return reservaAulaService.obtenerReservasAula();
    }

    @DeleteMapping("/{reservaId}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long reservaId) {
        reservaAulaService.cancelarReserva(reservaId);
        return ResponseEntity.noContent().build();
    }
}

