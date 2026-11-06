package com.efecto.web.mercado.victoria.dto.local;

import java.time.LocalDateTime;

public record LocalResponse(
   Long id,
   Long LocalPadreId,
   String nombre,
   String numero,
   String pasillo,
   String giroDeNegocio,
   Boolean esActivo,
   LocalDateTime creadoEl,
   Long locatarioId
) {
}
