package com.FChicken.factory;

import org.springframework.stereotype.Component;
import com.FChicken.model.Pedido;

@Component
public class PedidoFactory {

    public Pedido crearPedido(String infoPedido, Integer cantidad, Double precio, String direccion, String observaciones) {
        return Pedido.builder()
                .infoPedido(infoPedido)
                .cantidad(cantidad)
                .precio(precio)
                .direccion(direccion)
                .observaciones(observaciones)
                .build();
    }
}
