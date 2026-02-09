package com.FChicken.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FChicken.model.Inventario;
import com.FChicken.repository.InventarioRepository;
import com.FChicken.factory.InventarioFactory;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;
    private final InventarioFactory inventarioFactory;

    public InventarioService(InventarioRepository inventarioRepository, InventarioFactory inventarioFactory) {
        this.inventarioRepository = inventarioRepository;
        this.inventarioFactory = inventarioFactory;
    }

    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    public Inventario obtenerPorId(Long idInventario) {
        return inventarioRepository.findById(idInventario).orElse(null);
    }

    public Inventario registrar(Inventario inventarioRequest) {
        Inventario inventario = inventarioFactory.crearInventario(
                inventarioRequest.getTipo(),
                inventarioRequest.getNombre(),
                inventarioRequest.getPrecio(),
                inventarioRequest.getStock()
        );
        return inventarioRepository.save(inventario);
    }

    public Inventario actualizar(Long idInventario, Inventario inventarioRequest) {
        Optional<Inventario> optionalInventario = inventarioRepository.findById(idInventario);

        if (optionalInventario.isPresent()) {
            Inventario existente = optionalInventario.get();
            existente.setTipo(inventarioRequest.getTipo());
            existente.setNombre(inventarioRequest.getNombre());
            existente.setPrecio(inventarioRequest.getPrecio());
            existente.setStock(inventarioRequest.getStock());

            return inventarioRepository.save(existente);
        }
        return null;
    }

    public boolean eliminar(Long idInventario) {
        if (inventarioRepository.existsById(idInventario)) {
            inventarioRepository.deleteById(idInventario);
            return true;
        }
        return false;
    }
}
