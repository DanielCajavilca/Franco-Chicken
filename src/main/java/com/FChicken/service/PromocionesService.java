package com.FChicken.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FChicken.model.Promociones;
import com.FChicken.repository.PromocionesRepository;
import com.FChicken.factory.PromocionesFactory;

@Service
public class PromocionesService {

    private final PromocionesRepository promocionesRepository;
    private final PromocionesFactory promocionesFactory;

    public PromocionesService(PromocionesRepository promocionesRepository, PromocionesFactory promocionesFactory) {
        this.promocionesRepository = promocionesRepository;
        this.promocionesFactory = promocionesFactory;
    }

    public List<Promociones> obtenerTodos() {
        return promocionesRepository.findAll();
    }

    public Promociones obtenerPorId(Long id) {
        return promocionesRepository.findById(id).orElse(null);
    }

    public Promociones registrar(Promociones promocionesRequest) {
        Promociones promociones = promocionesFactory.crearPromocion(
                promocionesRequest.getNombre(),
                promocionesRequest.getDescripcion(),
                promocionesRequest.getFecha_ini(),
                promocionesRequest.getFecha_fin(),
                promocionesRequest.getTipo(),
                promocionesRequest.getEstado()
        );
        return promocionesRepository.save(promociones);
    }

    public Promociones actualizar(Long id, Promociones promocionesRequest) {
        Optional<Promociones> optionalPromociones = promocionesRepository.findById(id);

        if (optionalPromociones.isPresent()) {
            Promociones existente = optionalPromociones.get();
            existente.setNombre(promocionesRequest.getNombre());
            existente.setDescripcion(promocionesRequest.getDescripcion());
            existente.setFecha_ini(promocionesRequest.getFecha_ini());
            existente.setFecha_fin(promocionesRequest.getFecha_fin());
            existente.setTipo(promocionesRequest.getTipo());
            existente.setEstado(promocionesRequest.getEstado());

            return promocionesRepository.save(existente);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (promocionesRepository.existsById(id)) {
            promocionesRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
