package com.FChicken.service;

import com.FChicken.model.Cliente;
import com.FChicken.repository.ClienteRepository;
import com.FChicken.factory.ClienteFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

class ClienteServiceTest {

    private ClienteRepository clienteRepository;
    private ClienteFactory clienteFactory;
    private ClienteService clienteService;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        clienteFactory = mock(ClienteFactory.class);
        clienteService = new ClienteService(clienteRepository, clienteFactory);

        cliente = Cliente.builder()
                .idCliente(1L)
                .nombre("Pedro")
                .apellido("Perez")
                .telefono(987654321L)
                .direccion("Av. Lima")
                .build();
    }

    @Test
    void testObtenerTodos() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.obtenerTodos();

        assertEquals(1, resultado.size());
        assertEquals("Pedro", resultado.get(0).getNombre());
    }

    @Test
    void testObtenerPorId() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.obtenerPorId(1L);

        assertEquals("Pedro", resultado.getNombre());
    }

    @Test
    void testRegistrar() {
        when(clienteFactory.crearCliente(anyString(), anyString(), anyLong(), anyString()))
                .thenReturn(cliente);
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.registrar(cliente);

        assertEquals("Pedro", resultado.getNombre());
        verify(clienteFactory, times(1)).crearCliente(
                eq("Pedro"), eq("Perez"), eq(987654321L), eq("Av. Lima")
        );
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testActualizar() {
        Cliente cambios = Cliente.builder()
                .nombre("Juan")
                .apellido("Gomez")
                .telefono(999888777L)
                .direccion("Av. Arequipa")
                .build();

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente actualizado = clienteService.actualizar(1L, cambios);

        assertEquals("Juan", actualizado.getNombre());
        assertEquals("Gomez", actualizado.getApellido());
    }

    @Test
    void testEliminar() {
        when(clienteRepository.existsById(1L)).thenReturn(true);

        boolean resultado = clienteService.eliminar(1L);

        assertTrue(resultado);
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
