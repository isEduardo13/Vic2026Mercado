package com.efecto.web.mercado.victoria.security;

import com.efecto.web.mercado.victoria.dto.auth.LoginRequest;
import com.efecto.web.mercado.victoria.dto.auth.LoginResponse;
import com.efecto.web.mercado.victoria.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        org.springframework.security.core.Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.nombreUsuario(), request.contrasenia()));
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();

        String token = jwtService.generateToken(usuarioAutenticado);
        return LoginResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .nombreUsuario(usuarioAutenticado.getUsername())
                .role(usuarioAutenticado.getRole().getNombre())
                .build();
    }
}
