package com.efecto.web.mercado.victoria.dto.locatario;

import lombok.Builder;

@Builder
public record LocatarioResponse(
        Integer id,
        String nombre,
        String telefono) {

}
