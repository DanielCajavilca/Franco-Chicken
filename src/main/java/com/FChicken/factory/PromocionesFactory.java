package com.FChicken.factory;

import java.sql.Date;

import org.springframework.stereotype.Component;
import com.FChicken.model.Promociones;

@Component
public class PromocionesFactory {

    public Promociones crearPromocion(String nombre, String descripcion, Date fecha_ini, Date fecha_fin, String tipo, Boolean estado) {
        return Promociones.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .fecha_ini(fecha_ini)
                .fecha_fin(fecha_fin)
                .tipo(tipo)
                .estado(estado)
                .build();
    }
}