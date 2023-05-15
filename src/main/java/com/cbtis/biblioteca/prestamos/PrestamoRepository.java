package com.cbtis.biblioteca.prestamos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {

    public List<Prestamo> findAllByEstudianteNumeroControlAndEstadoPrestamo(Integer id, String estadoPrestamo);

    public List<Prestamo> findAllByEstudianteNumeroControl(Integer id);
}
