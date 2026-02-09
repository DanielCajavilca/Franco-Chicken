package com.FChicken.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.FChicken.model.Cliente;
import com.FChicken.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/Todos")
    public List<Cliente> obtenerTodos() {
        return clienteService.obtenerTodos();
    }

    @GetMapping("/id/{id}")
    public Cliente obtenerPorId(@PathVariable Long id) {
        return clienteService.obtenerPorId(id);
    }

    @PostMapping
    public Cliente registrar(@RequestBody Cliente cliente) {
        return clienteService.registrar(cliente);
    }

    @PutMapping("/id/{id}")
    public Cliente actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.actualizar(id, cliente);
    }

    @DeleteMapping("/id/{id}")
    public String eliminar(@PathVariable Long id) {
        return clienteService.eliminar(id)
                ? "Cliente eliminado con éxito"
                : "Cliente no encontrado";
    }
}
