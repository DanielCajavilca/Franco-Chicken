package com.FChicken.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.FChicken.model.Pedido;
import com.FChicken.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/Todos")
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/id/{id}")
    public Pedido obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id);
    }

    @PostMapping
    public Pedido registrar(@RequestBody Pedido pedido) {
        return pedidoService.registrar(pedido);
    }

    @PutMapping("/id/{id}")
    public Pedido actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.actualizar(id, pedido);
    }

    @DeleteMapping("/id/{id}")
    public String eliminar(@PathVariable Long id) {
        return pedidoService.eliminar(id)
                ? "Pedido eliminado con éxito"
                : "Pedido no encontrado";
    }
}
