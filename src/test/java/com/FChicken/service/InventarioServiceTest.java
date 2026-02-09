package com.FChicken.service;

import com.FChicken.model.Inventario;
import com.FChicken.repository.InventarioRepository;
import com.FChicken.factory.InventarioFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InventarioServiceTest {

    private InventarioRepository inventarioRepository;
    private InventarioFactory inventarioFactory;
    private InventarioService inventarioService;
    private Inventario inventario;

    @BeforeEach
    void setUp() {
        inventarioRepository = mock(InventarioRepository.class);
        inventarioFactory = mock(InventarioFactory.class);
        inventarioService = new InventarioService(inventarioRepository, inventarioFactory);

        inventario = Inventario.builder()
                .idIventario(1L)
                .tipo("Bebida")
                .nombre("Inca Kola")
                .precio(3.50)
                .stock(20L)
                .build();
    }

    @Test
    void testObtenerTodos() {
        when(inventarioRepository.findAll()).thenReturn(List.of(inventario));

        List<Inventario> result = inventarioService.obtenerTodos();

        assertEquals(1, result.size());
    }

    @Test
    void testObtenerPorId() {
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventario));

        Inventario result = inventarioService.obtenerPorId(1L);

        assertEquals("Inca Kola", result.getNombre());
    }

    @Test
    void testRegistrar() {
        when(inventarioFactory.crearInventario(anyString(), anyString(), anyDouble(), anyLong()))
                .thenReturn(inventario);
        when(inventarioRepository.save(any())).thenReturn(inventario);

        Inventario result = inventarioService.registrar(inventario);

        assertEquals(3.50, result.getPrecio());
        verify(inventarioFactory, times(1)).crearInventario(
                eq("Bebida"), eq("Inca Kola"), eq(3.50), eq(20L)
        );
        verify(inventarioRepository, times(1)).save(any(Inventario.class));
    }

    @Test
    void testActualizar() {
        Inventario cambios = Inventario.builder()
                .tipo("Bebida")
                .nombre("Coca Cola")
                .precio(4.00)
                .stock(15L)
                .build();

        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventario));
        when(inventarioRepository.save(any())).thenReturn(inventario);

        Inventario actualizado = inventarioService.actualizar(1L, cambios);

        assertEquals("Coca Cola", actualizado.getNombre());
    }

    @Test
    void testEliminar() {
        when(inventarioRepository.existsById(1L)).thenReturn(true);

        boolean deleted = inventarioService.eliminar(1L);

        assertTrue(deleted);
        verify(inventarioRepository).deleteById(1L);
    }
}
