package com.FChicken.service;

import com.FChicken.model.Pedido;
import com.FChicken.repository.PedidoRepository;
import com.FChicken.factory.PedidoFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PedidoServiceTest {

    private PedidoRepository pedidoRepository;
    private PedidoFactory pedidoFactory;
    private PedidoService pedidoService;
    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedidoRepository = mock(PedidoRepository.class);
        pedidoFactory = mock(PedidoFactory.class);
        pedidoService = new PedidoService(pedidoRepository, pedidoFactory);

        pedido = Pedido.builder()
                .idPedido(1L)
                .infoPedido("Pollo a la brasa")
                .cantidad(2)
                .precio(50.0)
                .direccion("Av. Lima")
                .observaciones("Sin picante")
                .build();
    }

    @Test
    void testObtenerTodos() {
        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<Pedido> result = pedidoService.obtenerTodos();

        assertEquals(1, result.size());
    }

    @Test
    void testObtenerPorId() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Pedido result = pedidoService.obtenerPorId(1L);

        assertEquals("Pollo a la brasa", result.getInfoPedido());
    }

    @Test
    void testRegistrar() {
        when(pedidoFactory.crearPedido(anyString(), anyInt(), anyDouble(), anyString(), anyString()))
                .thenReturn(pedido);
        when(pedidoRepository.save(any())).thenReturn(pedido);

        Pedido result = pedidoService.registrar(pedido);

        assertEquals(50.0, result.getPrecio());
        verify(pedidoFactory, times(1)).crearPedido(
                eq("Pollo a la brasa"), eq(2), eq(50.0), eq("Av. Lima"), eq("Sin picante")
        );
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void testActualizar() {
        Pedido cambios = Pedido.builder()
                .infoPedido("Chaufa")
                .cantidad(1)
                .precio(20.0)
                .direccion("Av. Arequipa")
                .observaciones("Ninguna")
                .build();

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));
        when(pedidoRepository.save(any())).thenReturn(pedido);

        Pedido actualizado = pedidoService.actualizar(1L, cambios);

        assertEquals("Chaufa", actualizado.getInfoPedido());
    }

    @Test
    void testEliminar() {
        when(pedidoRepository.existsById(1L)).thenReturn(true);

        boolean deleted = pedidoService.eliminar(1L);

        assertTrue(deleted);
        verify(pedidoRepository).deleteById(1L);
    }
}
