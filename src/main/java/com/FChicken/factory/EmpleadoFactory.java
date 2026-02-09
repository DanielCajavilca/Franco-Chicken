package com.FChicken.factory;

import org.springframework.stereotype.Component;
import com.FChicken.model.Empleado;

@Component
public class EmpleadoFactory {

    public Empleado crearEmpleado(String nombre, String apellido, Long telefono, String cargo, Double salario) {
        return Empleado.builder()
                .nombre(nombre)
                .apellido(apellido)
                .telefono(telefono)
                .cargo(cargo)
                .salario(salario)
                .build();
    }
}
