package com.FChicken.service;

import com.FChicken.model.Reseña;
import com.FChicken.repository.ReseñaRepository;
import com.FChicken.factory.ReseñaFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReseñaServiceTest {

    private ReseñaRepository reseñaRepository;
    private ReseñaFactory reseñaFactory;
    private ReseñaService reseñaService;
    private Reseña reseña;

    @BeforeEach
    void setUp() {
        reseñaRepository = mock(ReseñaRepository.class);
        reseñaFactory = mock(ReseñaFactory.class);
        reseñaService = new ReseñaService(reseñaRepository, reseñaFactory);

        reseña = Reseña.builder()
                .idReseña(1L)
                .nombre("Carlos")
                .comentario("Muy rico")
                .calificacion(4.5)
                .fecha(Date.valueOf("2025-01-10"))
                .build();
    }

    @Test
    void testObtenerTodos() {
        when(reseñaRepository.findAll()).thenReturn(List.of(reseña));

        List<Reseña> result = reseñaService.obtenerTodos();

        assertEquals(1, result.size());
    }

    @Test
    void testObtenerPorId() {
        when(reseñaRepository.findById(1L)).thenReturn(Optional.of(reseña));

        Reseña result = reseñaService.obtenerPorId(1L);

        assertEquals("Carlos", result.getNombre());
    }

    @Test
    void testRegistrar() {
        when(reseñaFactory.crearReseña(anyString(), anyString(), anyDouble(), any(Date.class)))
                .thenReturn(reseña);
        when(reseñaRepository.save(any())).thenReturn(reseña);

        Reseña result = reseñaService.registrar(reseña);

        assertEquals(4.5, result.getCalificacion());
        verify(reseñaFactory, times(1)).crearReseña(
                eq("Carlos"), eq("Muy rico"), eq(4.5), eq(Date.valueOf("2025-01-10"))
        );
        verify(reseñaRepository, times(1)).save(any(Reseña.class));
    }

    @Test
    void testActualizar() {
        Reseña cambios = Reseña.builder()
                .nombre("Luis")
                .comentario("Excelente atención")
                .calificacion(5.0)
                .fecha(Date.valueOf("2025-02-01"))
                .build();

        when(reseñaRepository.findById(1L)).thenReturn(Optional.of(reseña));
        when(reseñaRepository.save(any())).thenReturn(reseña);

        Reseña actualizado = reseñaService.actualizar(1L, cambios);

        assertEquals("Luis", actualizado.getNombre());
    }

    @Test
    void testEliminar() {
        when(reseñaRepository.existsById(1L)).thenReturn(true);

        boolean deleted = reseñaService.eliminar(1L);

        assertTrue(deleted);
        verify(reseñaRepository).deleteById(1L);
    }
}
