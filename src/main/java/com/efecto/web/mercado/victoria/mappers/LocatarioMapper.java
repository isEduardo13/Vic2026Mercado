package com.efecto.web.mercado.victoria.mappers;

import com.efecto.web.mercado.victoria.dto.locatario.LocatarioRequest;
import com.efecto.web.mercado.victoria.dto.locatario.LocatarioResponse;
import com.efecto.web.mercado.victoria.entities.Locatario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocatarioMapper {

    public Locatario requestToEntity(LocatarioRequest locatarioRequest){

        if (locatarioRequest == null) return null;

        return Locatario.builder()
                .nombre(locatarioRequest.nombre().trim())
                .telefono(locatarioRequest.telefono() != null && !locatarioRequest.telefono().isBlank()
                        ? locatarioRequest.telefono().trim() : null)
                .build();
    }

    public LocatarioResponse entityToResponse(Locatario locatario) {

        if (locatario == null) return null;

        return LocatarioResponse.builder()
                .id(locatario.getId())
                .nombre(locatario.getNombre())
                .telefono(locatario.getTelefono())
                .build();
    }
}
