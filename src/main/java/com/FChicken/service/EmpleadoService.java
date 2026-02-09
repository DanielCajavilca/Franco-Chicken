package com.FChicken.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FChicken.model.Empleado;
import com.FChicken.repository.EmpleadoRepository;
import com.FChicken.factory.EmpleadoFactory;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoFactory empleadoFactory;

    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoFactory empleadoFactory) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoFactory = empleadoFactory;
    }

    public List<Empleado> obtenerTodos() {
        return empleadoRepository.findAll();
    }

    public Empleado obtenerPorId(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado).orElse(null);
    }

    public Empleado obtenerPorCargo(String cargo) {
        return empleadoRepository.findByCargo(cargo).orElse(null);
    }

    public List<Empleado> obtenerTodosPorCargo(String cargo) {
        return empleadoRepository.findAllByCargo(cargo);
    }

    public Empleado registrar(Empleado empleadoRequest) {
        Empleado empleado = empleadoFactory.crearEmpleado(
                empleadoRequest.getNombre(),
                empleadoRequest.getApellido(),
                empleadoRequest.getTelefono(),
                empleadoRequest.getCargo(),
                empleadoRequest.getSalario()
        );
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizar(Long idEmpleado, Empleado empleadoRequest) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(idEmpleado);

        if (optionalEmpleado.isPresent()) {
            Empleado existente = optionalEmpleado.get();
            existente.setNombre(empleadoRequest.getNombre());
            existente.setApellido(empleadoRequest.getApellido());
            existente.setTelefono(empleadoRequest.getTelefono());
            existente.setCargo(empleadoRequest.getCargo());
            existente.setSalario(empleadoRequest.getSalario());

            return empleadoRepository.save(existente);
        }
        return null;
    }

    public boolean eliminar(Long idEmpleado) {
        if (empleadoRepository.existsById(idEmpleado)) {
            empleadoRepository.deleteById(idEmpleado);
            return true;
        }
        return false;
    }
}
