package com.FChicken.controller;

import org.springframework.web.bind.annotation.*;
import com.FChicken.model.Empleado;
import com.FChicken.service.EmpleadoService;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/Todos")
    public List<Empleado> obtenerTodos() {
        return empleadoService.obtenerTodos();
    }

    @GetMapping("/id/{id}")
    public Empleado obtenerPorId(@PathVariable Long id) {
        return empleadoService.obtenerPorId(id);
    }

    @GetMapping("/cargo/{cargo}")
    public Empleado obtenerPorCargo(@PathVariable String cargo) {
        return empleadoService.obtenerPorCargo(cargo);
    }

    @GetMapping("/Todos/{cargo}")
    public List<Empleado> obtenerTodosPorCargo(@PathVariable String cargo) {
        return empleadoService.obtenerTodosPorCargo(cargo);
    }

    @PostMapping
    public Empleado registrar(@RequestBody Empleado empleado) {
        return empleadoService.registrar(empleado);
    }

    @PutMapping("/id/{id}")
    public Empleado actualizar(@PathVariable Long id, @RequestBody Empleado empleado) {
        return empleadoService.actualizar(id, empleado);
    }

    @DeleteMapping("/id/{id}")
    public String eliminar(@PathVariable Long id) {
        return empleadoService.eliminar(id) 
                ? "Empleado eliminado con éxito" 
                : "Empleado no encontrado";
    }
}
