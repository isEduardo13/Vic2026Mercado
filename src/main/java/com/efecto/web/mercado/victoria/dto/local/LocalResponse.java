package com.efecto.web.mercado.victoria.dto.local;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LocalResponse(
   Long id,
   Long localPadreId,
   String nombre,
   String numero,
   String pasillo,
   String giroDeNegocio,
   Boolean esActivo,
   LocalDateTime creadoEl,
   Long locatarioId
) {
}
