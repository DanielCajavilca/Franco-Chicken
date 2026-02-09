package com.FChicken.factory;

import org.springframework.stereotype.Component;
import com.FChicken.model.Inventario;

@Component
public class InventarioFactory {

    public Inventario crearInventario(String tipo, String nombre, Double precio, Long stock) {
        return Inventario.builder()
                .tipo(tipo)
                .nombre(nombre)
                .precio(precio)
                .stock(stock)
                .build();
    }
}
