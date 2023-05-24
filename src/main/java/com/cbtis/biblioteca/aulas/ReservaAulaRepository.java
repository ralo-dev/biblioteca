package com.cbtis.biblioteca.aulas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ReservaAulaRepository extends JpaRepository<ReservaAula, Long> {
    List<ReservaAula> findByFechaReservaAndHoraFinGreaterThanEqual(LocalDate fechaReserva, LocalTime horaFin);

    List<ReservaAula> findByFechaReserva(LocalDate fechaReserva);
}
