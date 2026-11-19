package com.efecto.web.mercado.victoria.security;

import com.efecto.web.mercado.victoria.entities.Permiso;
import com.efecto.web.mercado.victoria.entities.Role;
import com.efecto.web.mercado.victoria.entities.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDetailsTest {

    @Test
    void exponeRoleYPermisosComoAuthorities() {
        Permiso permiso = Permiso.builder().nombre("LOCAL_LISTAR").build();
        Role role = Role.builder().nombre("OPERADOR").permisos(Set.of(permiso)).build();
        Usuario usuario = Usuario.builder()
                .nombreUsuario("operador")
                .contrasenia("hash")
                .activo(true)
                .role(role)
                .build();

        assertTrue(usuario.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_OPERADOR")));
        assertTrue(usuario.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("LOCAL_LISTAR")));
        assertTrue(usuario.isEnabled());
    }

    @Test
    void usuarioInactivoNoEstaHabilitado() {
        Usuario usuario = Usuario.builder()
                .nombreUsuario("bloqueado")
                .contrasenia("hash")
                .activo(false)
                .role(Role.builder().nombre("ADMIN").build())
                .build();

        assertFalse(usuario.isEnabled());
        assertFalse(usuario.isAccountNonLocked());
    }
}
