package com.FChicken.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.FChicken.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findByCargo(String cargo);
    List<Empleado> findAllByCargo(String cargo);
}
