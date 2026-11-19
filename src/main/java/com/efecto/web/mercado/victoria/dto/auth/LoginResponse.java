package com.efecto.web.mercado.victoria.dto.auth;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        String tokenType,
        String nombreUsuario,
        String role
) {
}
