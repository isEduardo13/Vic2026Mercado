package com.efecto.web.mercado.victoria.services;

import com.efecto.web.mercado.victoria.dto.locatario.LocatarioRequest;
import com.efecto.web.mercado.victoria.dto.locatario.LocatarioResponse;
import com.efecto.web.mercado.victoria.entities.Locatario;
import com.efecto.web.mercado.victoria.exception.RecursoNoEncontradoException;
import com.efecto.web.mercado.victoria.mappers.LocatarioMapper;
import com.efecto.web.mercado.victoria.repositories.LocatarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LocatarioImplement implements LocatarioService {

    private final LocatarioRepository locatarioRepository;
    private final LocatarioMapper locatarioMapper;

    @Override
    public List<LocatarioResponse> obtenerLocatarios() {
        log.info("Iniciando obtenerLocatarios");

        return  locatarioRepository.findAll().stream()
                .map(locatarioMapper::entityToResponse)
                .toList();
    }

    @Override
    public LocatarioResponse obtenerLocatario(Long id) {
        log.info("Iniciando obtenerLocatario {}", id);
        return locatarioMapper.entityToResponse(obtenerLocatarioOLanzarExcepcion(id));
    }

    @Override
    public LocatarioResponse registrarLocatario(LocatarioRequest locatarioRequest) {
        return null;
    }

    @Override
    public LocatarioResponse actualizarLocatario(Long id, LocatarioRequest locatarioRequest) {
        return null;
    }

    @Override
    public void eliminarLocatario(Long id) {

    }

    private Locatario obtenerLocatarioOLanzarExcepcion(Long id) {
        return locatarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Locatario no encontrado"));
    }
}
