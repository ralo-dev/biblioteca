package com.cbtis.biblioteca.aulas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaAulaService {
    private final ReservaAulaRepository reservaAulaRepository;

    @Autowired
    public ReservaAulaService(ReservaAulaRepository reservaAulaRepository) {
        this.reservaAulaRepository = reservaAulaRepository;
    }

    public ReservaAula actualizarReserva(ReservaAulaRequest request, Long reservaId){
        Optional<ReservaAula> reservaAnterior = reservaAulaRepository.findById(reservaId);
        if (!reservaAnterior.isPresent()) {
            return null;
        }
        LocalDate fecha = LocalDate.parse(request.getFechaReserva());
        LocalTime inicio = LocalTime.parse(request.getHoraInicio());
        LocalTime fin = LocalTime.parse(request.getHoraFin());
        String nombreProfesor = request.getNombreProfesor();
        String grupo = request.getGrupo();
        String materia = request.getMateria();
        return reservarAula(reservaId, nombreProfesor, fecha, inicio, fin, grupo, materia);
    }

    public ReservaAula reservarAula(Long id, String nombreProfesor, LocalDate fechaReserva, LocalTime horaInicio, LocalTime horaFin, String grupo, String materia) {

        if (!puedeReservar(fechaReserva) || !esHoraValida(horaInicio, horaFin)) {
            return null;
        }

        if (horaInicio.isBefore(LocalTime.of(7, 0)) || horaFin.isAfter(LocalTime.of(16, 0))) {
            return null;
        }

        List<ReservaAula> reservasExistente = reservaAulaRepository.findByFechaReserva(fechaReserva);

        if (id != null) {
            reservasExistente.removeIf(reserva -> reserva.getId().equals(id));
        }
        // Verificar si hay alguna reserva existente que se superponga con el intervalo de tiempo proporcionado
        boolean haySuperposicion = reservasExistente.stream()
                .anyMatch(reserva -> {
                    LocalTime inicioExistente = reserva.getHoraInicio();
                    LocalTime finExistente = reserva.getHoraFin();
                    return !(horaFin.isBefore(inicioExistente) || horaInicio.isAfter(finExistente));
                });

        if (!haySuperposicion) {
            ReservaAula reservaAula = new ReservaAula();
            reservaAula.setId(id);
            reservaAula.setNombreProfesor(nombreProfesor);
            reservaAula.setFechaReserva(fechaReserva);
            reservaAula.setHoraInicio(horaInicio);
            reservaAula.setHoraFin(horaFin);
            reservaAula.setGrupo(grupo);
            reservaAula.setMateria(materia);
            return reservaAulaRepository.save(reservaAula);
        }

        return null;
    }

    public boolean puedeReservar(LocalDate fechaReserva) {
        DayOfWeek dayOfWeek = fechaReserva.getDayOfWeek();
        LocalDate currentDate = LocalDate.now();
        int daysUntilReservation = currentDate.until(fechaReserva).getDays();

        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && daysUntilReservation <= 7;
    }

    public boolean esHoraValida(LocalTime horaInicio, LocalTime horaFin) {
        return horaInicio.isBefore(horaFin);
    }

    public List<ReservaAula> obtenerReservasAula() {
        return reservaAulaRepository.findAll();
    }

    public void cancelarReserva(Long reservaId) {
        reservaAulaRepository.deleteById(reservaId);
    }

    public List<DisponibilidadAula> consultarHorasDisponibles() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.plusDays(7);

        List<DisponibilidadAula> disponibilidadAulaList = new ArrayList<>();

        while (currentDate.isBefore(endDate)) {
            if (currentDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || currentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                currentDate = currentDate.plusDays(1);
                continue;
            }
            List<ReservaAula> reservas = reservaAulaRepository.findByFechaReserva(currentDate);
            DisponibilidadAula disponibilidadAula = new DisponibilidadAula();
            disponibilidadAula.setFecha(currentDate);
            disponibilidadAula.setHorasDisponibles(obtenerHorasDisponibles(reservas));
            disponibilidadAulaList.add(disponibilidadAula);
            currentDate = currentDate.plusDays(1);
        }

        return disponibilidadAulaList;
    }

    private List<LocalTime> obtenerHorasDisponibles(List<ReservaAula> reservas) {
        List<LocalTime> horasDisponibles = new ArrayList<>();
        LocalTime horaInicio = LocalTime.of(7, 0);
        LocalTime horaFin = LocalTime.of(16, 0);

        for (ReservaAula reserva : reservas) {
            LocalTime inicioReserva = reserva.getHoraInicio();
            LocalTime finReserva = reserva.getHoraFin();

            // Verificar si hay espacio disponible entre la horaInicio actual y el inicio de la reserva
            if (inicioReserva.isAfter(horaInicio)) {
                while (horaInicio.isBefore(inicioReserva)) {
                    horasDisponibles.add(horaInicio);
                    horaInicio = horaInicio.plusHours(1);
                }
            }

            // Actualizar la horaInicio al final de la reserva
            horaInicio = finReserva;
        }

        // Agregar las horas disponibles restantes hasta la horaFin
        while (horaInicio.isBefore(horaFin)) {
            horasDisponibles.add(horaInicio);
            horaInicio = horaInicio.plusHours(1);
        }

        return horasDisponibles;
    }



}

