package com.cbtis.biblioteca.prestamos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    public List<Prestamo> findAllByEstudianteNumeroControlAndEstadoPrestamo(String id, String estadoPrestamo);

    public List<Prestamo> findAllByEstudianteNumeroControl(String id);
}
