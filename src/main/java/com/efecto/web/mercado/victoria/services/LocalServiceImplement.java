package com.efecto.web.mercado.victoria.services;

import com.efecto.web.mercado.victoria.dto.local.LocalRequest;
import com.efecto.web.mercado.victoria.dto.local.LocalResponse;
import com.efecto.web.mercado.victoria.dto.local.LocalUpdateRequest;
import com.efecto.web.mercado.victoria.entities.Local;
import com.efecto.web.mercado.victoria.entities.Locatario;
import com.efecto.web.mercado.victoria.exception.RecursoNoEncontradoException;
import com.efecto.web.mercado.victoria.mappers.LocalMapper;
import com.efecto.web.mercado.victoria.repositories.LocalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LocalServiceImplement implements LocalService{

    private final LocalRepository localRepository;
    private final LocalMapper localMapper;


    @Override
    public List<LocalResponse> obtenerLocales() {
        log.info("Iniciando obtenerLocales");

        return localRepository.findAll().stream()
                .map(localMapper::entityToResponse)
                .toList();


    }

    @Override
    public LocalResponse obtenerLocalPorId(Long id) {
        log.info("Iniciando obtenerLocalPorId");
        return localMapper.entityToResponse(obtenerLocalOLanzarExcepcion(id));
    }

    @Override
    public LocalResponse registrarLocal(LocalRequest localRequest) {
        log.info("Iniciando registrarLocal");
        Local local = localMapper.requestToEntity(localRequest);
        localRepository.save(local);
       return localMapper.entityToResponse(local);
    }

    @Override
    public LocalResponse actualizarUbicacionLocal(Long id, LocalRequest localRequest) {
       log.info("Iniciando actualizarUbicacionLocal");

       Local local = obtenerLocalOLanzarExcepcion(id);
       local.actualizarUbicacion(localRequest.numero(), localRequest.pasillo());
       localRepository.save(local);
       return localMapper.entityToResponse(local);

    }

    @Override
    public LocalResponse actualizarLocal(Long id, LocalUpdateRequest request) {
        Local local = obtenerLocalOLanzarExcepcion(id);
        local.actualizarDatos(request.nombre(), request.giroDeNegocio());
        localRepository.save(local);
        return localMapper.entityToResponse(local);
    }

    @Override
    public void desactivarLocal(Long id) {
        Local local = obtenerLocalOLanzarExcepcion(id);

        local.actualizarEstado(false);
        localRepository.save(local);


    }

    private Local obtenerLocalOLanzarExcepcion(Long id) {
        return localRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Local no encontrado"));
    }

}
