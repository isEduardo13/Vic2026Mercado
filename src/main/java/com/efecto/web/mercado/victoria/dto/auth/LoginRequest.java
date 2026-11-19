package com.efecto.web.mercado.victoria.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "El nombre de usuario no puede estar vacío")
        String nombreUsuario,
        @NotBlank(message = "La contraseña no puede estar vacía")
        String contrasenia
) {
}
