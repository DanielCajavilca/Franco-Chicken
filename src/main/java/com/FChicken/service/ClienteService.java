package com.FChicken.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FChicken.model.Cliente;
import com.FChicken.repository.ClienteRepository;
import com.FChicken.factory.ClienteFactory;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteFactory clienteFactory;

    public ClienteService(ClienteRepository clienteRepository, ClienteFactory clienteFactory) {
        this.clienteRepository = clienteRepository;
        this.clienteFactory = clienteFactory;
    }

    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente registrar(Cliente clienteRequest) {
        Cliente cliente = clienteFactory.crearCliente(
                clienteRequest.getNombre(),
                clienteRequest.getApellido(),
                clienteRequest.getTelefono(),
                clienteRequest.getDireccion()
        );
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Long id, Cliente clienteRequest) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente existente = optionalCliente.get();

            existente.setNombre(clienteRequest.getNombre());
            existente.setApellido(clienteRequest.getApellido());
            existente.setTelefono(clienteRequest.getTelefono());
            existente.setDireccion(clienteRequest.getDireccion());

            return clienteRepository.save(existente);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
