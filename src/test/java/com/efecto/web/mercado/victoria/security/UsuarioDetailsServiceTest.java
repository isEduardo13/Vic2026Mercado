package com.efecto.web.mercado.victoria.security;

import com.efecto.web.mercado.victoria.entities.Usuario;
import com.efecto.web.mercado.victoria.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioDetailsServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioDetailsService usuarioDetailsService;

    @Test
    void cargaUsuarioExistente() {
        Usuario usuario = Usuario.builder().nombreUsuario("admin").activo(true).build();
        when(usuarioRepository.findByNombreUsuario("admin")).thenReturn(Optional.of(usuario));

        assertSame(usuario, usuarioDetailsService.loadUserByUsername("admin"));
    }

    @Test
    void lanzaExcepcionCuandoUsuarioNoExiste() {
        when(usuarioRepository.findByNombreUsuario("nadie")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class,
                () -> usuarioDetailsService.loadUserByUsername("nadie"));
    }
}
