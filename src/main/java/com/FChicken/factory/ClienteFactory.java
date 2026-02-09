package com.FChicken.factory;

import org.springframework.stereotype.Component;
import com.FChicken.model.Cliente;

@Component
public class ClienteFactory {

    public Cliente crearCliente(String nombre, String apellido, Long telefono, String direccion) {
        return Cliente.builder()
                .nombre(nombre)
                .apellido(apellido)
                .telefono(telefono)
                .direccion(direccion)
                .build();
    }
}
