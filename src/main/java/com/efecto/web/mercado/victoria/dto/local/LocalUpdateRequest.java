package com.efecto.web.mercado.victoria.dto.local;

import jakarta.validation.constraints.NotBlank;

public record LocalUpdateRequest (
        @NotBlank String nombre,
        @NotBlank String giroDeNegocio
){
}
