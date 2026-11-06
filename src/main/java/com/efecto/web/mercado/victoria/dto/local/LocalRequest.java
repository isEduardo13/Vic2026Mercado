package com.efecto.web.mercado.victoria.dto.local;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record LocalRequest(
        @NotNull(message = "El id del local padre no puede ser nulo")
        Long LocalPadreId,
        @NotBlank(message = "El nombre del local no puede estar en blanco")
        String nombre,
        @NotBlank(message = "El número del local no puede estar en blanco")
        String numero,
        @NotBlank(message = "El pasillo del local no puede estar en blanco")
        String pasillo,
        @NotBlank(message = "El giro de negocio del local no puede estar en blanco")
        String giroDeNegocio,
        @NotNull(message = "El estado del local no puede ser nulo")
        Boolean esActivo,
        @NotEmpty(message = "El id del locatario no puede estar vacío")
        Long locatarioId
) {
}
