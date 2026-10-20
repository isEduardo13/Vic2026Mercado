package com.efecto.web.mercado.victoria.dto.locatario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record LocatarioRequest(

        @NotNull(message = "El nombre no puede ser nulo")
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,
        @Pattern(regexp = "^$|^\\+\\d{1,3}-\\d{10}$", message = "El teléfono debe tener el formato +<código de país>-<número de 10 dígitos>")
        String telefono
) {
}
