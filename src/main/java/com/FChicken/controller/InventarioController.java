package com.FChicken.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.FChicken.model.Inventario;
import com.FChicken.service.InventarioService;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/Todos")
    public List<Inventario> obtenerTodos() {
        return inventarioService.obtenerTodos();
    }

    @GetMapping("/id/{id}")
    public Inventario obtenerPorId(@PathVariable Long id) {
        return inventarioService.obtenerPorId(id);
    }

    @PostMapping
    public Inventario registrar(@RequestBody Inventario inventario) {
        return inventarioService.registrar(inventario);
    }

    @PutMapping("/id/{id}")
    public Inventario actualizar(@PathVariable Long id, @RequestBody Inventario inventario) {
        return inventarioService.actualizar(id, inventario);
    }

    @DeleteMapping("/id/{id}")
    public String eliminar(@PathVariable Long id) {
        return inventarioService.eliminar(id)
                ? "Elemento eliminado con éxito"
                : "Elemento no encontrado";
    }
}
