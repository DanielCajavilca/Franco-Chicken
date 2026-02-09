package com.FChicken.service;

import com.FChicken.model.Empleado;
import com.FChicken.repository.EmpleadoRepository;
import com.FChicken.factory.EmpleadoFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoServiceTest {

    private EmpleadoRepository empleadoRepository;
    private EmpleadoFactory empleadoFactory;
    private EmpleadoService empleadoService;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleadoRepository = mock(EmpleadoRepository.class);
        empleadoFactory = mock(EmpleadoFactory.class);
        empleadoService = new EmpleadoService(empleadoRepository, empleadoFactory);

        empleado = Empleado.builder()
                .idEmpleado(1L)
                .nombre("Juan")
                .apellido("Perez")
                .telefono(987654321L)
                .cargo("Cocinero")
                .salario(1500.0)
                .build();
    }

    @Test
    void testObtenerTodos() {
        when(empleadoRepository.findAll()).thenReturn(List.of(empleado));

        List<Empleado> result = empleadoService.obtenerTodos();

        assertEquals(1, result.size());
    }

    @Test
    void testObtenerPorId() {
        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        Empleado result = empleadoService.obtenerPorId(1L);

        assertEquals("Juan", result.getNombre());
    }

    @Test
    void testRegistrar() {
        when(empleadoFactory.crearEmpleado(anyString(), anyString(), anyLong(), anyString(), anyDouble()))
                .thenReturn(empleado);
        when(empleadoRepository.save(any())).thenReturn(empleado);

        Empleado result = empleadoService.registrar(empleado);

        assertEquals("Perez", result.getApellido());
        verify(empleadoFactory, times(1)).crearEmpleado(
                eq("Juan"), eq("Perez"), eq(987654321L), eq("Cocinero"), eq(1500.0)
        );
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void testActualizar() {
        Empleado cambios = Empleado.builder()
                .nombre("Luis")
                .apellido("Gomez")
                .telefono(999888777L)
                .cargo("Cajero")
                .salario(1700.0)
                .build();

        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
        when(empleadoRepository.save(any())).thenReturn(empleado);

        Empleado actualizado = empleadoService.actualizar(1L, cambios);

        assertEquals("Luis", actualizado.getNombre());
    }

    @Test
    void testEliminar() {
        when(empleadoRepository.existsById(1L)).thenReturn(true);

        boolean deleted = empleadoService.eliminar(1L);

        assertTrue(deleted);
        verify(empleadoRepository).deleteById(1L);
    }
}
