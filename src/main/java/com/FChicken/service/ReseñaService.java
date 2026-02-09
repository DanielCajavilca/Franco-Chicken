package com.FChicken.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.FChicken.model.Reseña;
import com.FChicken.repository.ReseñaRepository;
import com.FChicken.factory.ReseñaFactory;

@Service
public class ReseñaService {

    private final ReseñaRepository reseñaRepository;
    private final ReseñaFactory reseñaFactory;

    public ReseñaService(ReseñaRepository reseñaRepository, ReseñaFactory reseñaFactory) {
        this.reseñaRepository = reseñaRepository;
        this.reseñaFactory = reseñaFactory;
    }

    public List<Reseña> obtenerTodos() {
        return reseñaRepository.findAll();
    }

    public Reseña obtenerPorId(Long id) {
        return reseñaRepository.findById(id).orElse(null);
    }

    public Reseña registrar(Reseña reseñaRequest) {
        Reseña reseña = reseñaFactory.crearReseña(
                reseñaRequest.getNombre(),
                reseñaRequest.getComentario(),
                reseñaRequest.getCalificacion(),
                reseñaRequest.getFecha()
        );
        return reseñaRepository.save(reseña);
    }

    public Reseña actualizar(Long id, Reseña reseñaRequest) {
        Optional<Reseña> optionalReseña = reseñaRepository.findById(id);

        if (optionalReseña.isPresent()) {
            Reseña existente = optionalReseña.get();
            existente.setNombre(reseñaRequest.getNombre());
            existente.setComentario(reseñaRequest.getComentario());
            existente.setCalificacion(reseñaRequest.getCalificacion());
            existente.setFecha(reseñaRequest.getFecha());

            return reseñaRepository.save(existente);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (reseñaRepository.existsById(id)) {
            reseñaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
