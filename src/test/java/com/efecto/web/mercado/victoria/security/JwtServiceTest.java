package com.efecto.web.mercado.victoria.security;

import com.efecto.web.mercado.victoria.entities.Role;
import com.efecto.web.mercado.victoria.entities.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService(
            "secreto-de-prueba-con-al-menos-32-caracteres-2026",
            3600000);

    @Test
    void generaYValidaToken() {
        Usuario usuario = Usuario.builder()
                .nombreUsuario("admin")
                .contrasenia("hash")
                .activo(true)
                .role(Role.builder().nombre("ADMIN").build())
                .build();

        String token = jwtService.generateToken(usuario);

        assertNotNull(token);
        assertEquals("admin", jwtService.extractUsername(token));
        assertTrue(jwtService.isTokenValid(token, usuario));
    }

    @Test
    void rechazaTokenDeOtroUsuario() {
        Usuario usuario = Usuario.builder()
                .nombreUsuario("admin")
                .contrasenia("hash")
                .activo(true)
                .role(Role.builder().nombre("ADMIN").build())
                .build();
        Usuario otro = Usuario.builder()
                .nombreUsuario("otro")
                .contrasenia("hash")
                .activo(true)
                .role(Role.builder().nombre("ADMIN").build())
                .build();

        String token = jwtService.generateToken(usuario);

        assertFalse(jwtService.isTokenValid(token, otro));
    }
}
