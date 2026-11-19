package com.efecto.web.mercado.victoria.mappers;

import com.efecto.web.mercado.victoria.dto.local.LocalRequest;
import com.efecto.web.mercado.victoria.dto.local.LocalResponse;
import com.efecto.web.mercado.victoria.entities.Local;
import com.efecto.web.mercado.victoria.entities.Locatario;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LocalMapper {

    public Local requestToEntity(LocalRequest request) {
        if (request == null) return null;

        Locatario locatario = null;
        if (request.locatarioId() != null) {
            locatario = Locatario.builder().id(request.locatarioId()).build();
        }
        return requestToEntity(request, locatario);
    }

    public Local requestToEntity(LocalRequest request, Locatario locatario) {
        if (request == null) return null;

        return Local.builder()
                .localPadreId(request.locatarioId())
                .nombre(request.nombre().trim())
                .numero(request.numero().trim())
                .pasillo(request.pasillo().trim())
                .giroDeNegocio(request.giroDeNegocio().trim())
                .esActivo(request.esActivo())
                .creadoEl(LocalDateTime.now())
                .locatario(locatario)
                .build();
    }

    public LocalResponse entityToResponse(Local local) {
        if (local == null) return null;

        return LocalResponse.builder()
                .id(local.getId())
                .localPadreId(local.getLocalPadreId())
                .nombre(local.getNombre())
                .numero(local.getNumero())
                .pasillo(local.getPasillo())
                .giroDeNegocio(local.getGiroDeNegocio())
                .esActivo(local.getEsActivo())
                .creadoEl(local.getCreadoEl())
                .locatarioId(local.getLocatario() != null ? local.getLocatario().getId() : null)
                .build();
    }
}
