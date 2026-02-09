package com.FChicken.service;

import com.FChicken.model.Promociones;
import com.FChicken.repository.PromocionesRepository;
import com.FChicken.factory.PromocionesFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PromocionesServiceTest {

    private PromocionesRepository promocionesRepository;
    private PromocionesFactory promocionesFactory;
    private PromocionesService promocionesService;
    private Promociones promo;

    @BeforeEach
    void setUp() {
        promocionesRepository = mock(PromocionesRepository.class);
        promocionesFactory = mock(PromocionesFactory.class);
        promocionesService = new PromocionesService(promocionesRepository, promocionesFactory);

        promo = Promociones.builder()
                .idPromociones(1L)
                .nombre("Promo 2x1")
                .descripcion("Dos por uno en pollos")
                .fecha_ini(Date.valueOf("2025-01-01"))
                .fecha_fin(Date.valueOf("2025-01-31"))
                .tipo("Descuento")
                .estado(true)
                .build();
    }

    @Test
    void testObtenerTodos() {
        when(promocionesRepository.findAll()).thenReturn(List.of(promo));

        List<Promociones> result = promocionesService.obtenerTodos();

        assertEquals(1, result.size());
    }

    @Test
    void testObtenerPorId() {
        when(promocionesRepository.findById(1L)).thenReturn(Optional.of(promo));

        Promociones result = promocionesService.obtenerPorId(1L);

        assertEquals("Promo 2x1", result.getNombre());
    }

    @Test
    void testRegistrar() {
        when(promocionesFactory.crearPromocion(anyString(), anyString(), any(Date.class), any(Date.class), anyString(), anyBoolean()))
                .thenReturn(promo);
        when(promocionesRepository.save(any())).thenReturn(promo);

        Promociones result = promocionesService.registrar(promo);

        assertEquals("Descuento", result.getTipo());
        verify(promocionesFactory, times(1)).crearPromocion(
                eq("Promo 2x1"), eq("Dos por uno en pollos"),
                eq(Date.valueOf("2025-01-01")), eq(Date.valueOf("2025-01-31")),
                eq("Descuento"), eq(true)
        );
        verify(promocionesRepository, times(1)).save(any(Promociones.class));
    }

    @Test
    void testActualizar() {
        Promociones cambios = Promociones.builder()
                .nombre("Promo 3x2")
                .descripcion("Tres por dos")
                .fecha_ini(Date.valueOf("2025-02-01"))
                .fecha_fin(Date.valueOf("2025-02-28"))
                .tipo("Oferta")
                .estado(true)
                .build();

        when(promocionesRepository.findById(1L)).thenReturn(Optional.of(promo));
        when(promocionesRepository.save(any())).thenReturn(promo);

        Promociones actualizado = promocionesService.actualizar(1L, cambios);

        assertEquals("Promo 3x2", actualizado.getNombre());
    }

    @Test
    void testEliminar() {
        when(promocionesRepository.existsById(1L)).thenReturn(true);

        boolean deleted = promocionesService.eliminar(1L);

        assertTrue(deleted);
        verify(promocionesRepository).deleteById(1L);
    }
}
