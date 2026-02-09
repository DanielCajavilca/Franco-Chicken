package com.FChicken.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.FChicken.model.Promociones;
import com.FChicken.service.PromocionesService;

@RestController
@RequestMapping("/promociones")
public class PromocionesController {

    private final PromocionesService promocionesService;

    public PromocionesController(PromocionesService promocionesService) {
        this.promocionesService = promocionesService;
    }

    @GetMapping("/Todos")
    public List<Promociones> obtenerTodos() {
        return promocionesService.obtenerTodos();
    }

    @GetMapping("/id/{id}")
    public Promociones obtenerPorId(@PathVariable Long id) {
        return promocionesService.obtenerPorId(id);
    }

    @PostMapping
    public Promociones registrar(@RequestBody Promociones promociones) {
        return promocionesService.registrar(promociones);
    }

    @PutMapping("/id/{id}")
    public Promociones actualizar(@PathVariable Long id, @RequestBody Promociones promociones) {
        return promocionesService.actualizar(id, promociones);
    }

    @DeleteMapping("/id/{id}")
    public String eliminar(@PathVariable Long id) {
        return promocionesService.eliminar(id)
                ? "Promoción eliminada con éxito"
                : "Promoción no encontrada";
    }
}
