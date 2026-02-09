package com.FChicken.factory;

import org.springframework.stereotype.Component;
import com.FChicken.model.Reseña;
import java.sql.Date;

@Component
public class ReseñaFactory {

    public Reseña crearReseña(String nombre, String comentario, Double calificacion, Date fecha) {
        return Reseña.builder()
                .nombre(nombre)
                .comentario(comentario)
                .calificacion(calificacion)
                .fecha(fecha)
                .build();
    }
}
