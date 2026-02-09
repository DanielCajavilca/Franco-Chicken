package com.FChicken.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.FChicken.model.Reseña;
import com.FChicken.service.ReseñaService;

@RestController
@RequestMapping("/reseña")
public class ReseñaController {

    private final ReseñaService reseñaService;

    public ReseñaController(ReseñaService reseñaService) {
        this.reseñaService = reseñaService;
    }

    @GetMapping("/Todos")
    public List<Reseña> obtenerTodos() {
        return reseñaService.obtenerTodos();
    }

    @GetMapping("/id/{id}")
    public Reseña obtenerPorId(@PathVariable Long id) {
        return reseñaService.obtenerPorId(id);
    }

    @PostMapping
    public Reseña registrar(@RequestBody Reseña reseña) {
        return reseñaService.registrar(reseña);
    }

    @PutMapping("/id/{id}")
    public Reseña actualizar(@PathVariable Long id, @RequestBody Reseña reseña) {
        return reseñaService.actualizar(id, reseña);
    }

    @DeleteMapping("/id/{id}")
    public String eliminar(@PathVariable Long id) {
        return reseñaService.eliminar(id)
                ? "Reseña eliminada con éxito"
                : "Reseña no encontrada";
    }
}
