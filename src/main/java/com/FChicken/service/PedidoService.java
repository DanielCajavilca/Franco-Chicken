package com.FChicken.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FChicken.model.Pedido;
import com.FChicken.repository.PedidoRepository;
import com.FChicken.factory.PedidoFactory;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoFactory pedidoFactory;

    public PedidoService(PedidoRepository pedidoRepository, PedidoFactory pedidoFactory) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoFactory = pedidoFactory;
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(Long idPedido) {
        return pedidoRepository.findById(idPedido).orElse(null);
    }

    public Pedido registrar(Pedido pedidoRequest) {
        Pedido pedido = pedidoFactory.crearPedido(
                pedidoRequest.getInfoPedido(),
                pedidoRequest.getCantidad(),
                pedidoRequest.getPrecio(),
                pedidoRequest.getDireccion(),
                pedidoRequest.getObservaciones()
        );
        return pedidoRepository.save(pedido);
    }

    public Pedido actualizar(Long idPedido, Pedido pedidoRequest) {
        Optional<Pedido> optionalPedido = pedidoRepository.findById(idPedido);

        if (optionalPedido.isPresent()) {
            Pedido existente = optionalPedido.get();
            existente.setInfoPedido(pedidoRequest.getInfoPedido());
            existente.setCantidad(pedidoRequest.getCantidad());
            existente.setPrecio(pedidoRequest.getPrecio());
            existente.setDireccion(pedidoRequest.getDireccion());
            existente.setObservaciones(pedidoRequest.getObservaciones());

            return pedidoRepository.save(existente);
        }
        return null;
    }

    public boolean eliminar(Long idPedido) {
        if (pedidoRepository.existsById(idPedido)) {
            pedidoRepository.deleteById(idPedido);
            return true;
        }
        return false;
    }
}
