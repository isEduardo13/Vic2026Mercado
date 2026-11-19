package com.efecto.web.mercado.victoria.controllers;

import com.efecto.web.mercado.victoria.dto.local.LocalRequest;
import com.efecto.web.mercado.victoria.dto.local.LocalResponse;
import com.efecto.web.mercado.victoria.dto.local.LocalUpdateRequest;
import com.efecto.web.mercado.victoria.services.LocalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocalControllerTest {

    @Mock
    private LocalService localService;

    @InjectMocks
    private LocalController localController;

    @Test
    void listarLocales_debeRetornarLista() {
        LocalResponse response = LocalResponse.builder().id(1L).nombre("Local 1").build();
        when(localService.obtenerLocales()).thenReturn(List.of(response));

        ResponseEntity<List<LocalResponse>> resultado = localController.listarLocales();

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals(List.of(response), resultado.getBody());
        verify(localService).obtenerLocales();
    }

    @Test
    void obtenerLocal_debeRetornarLocalPorId() {
        LocalResponse response = LocalResponse.builder().id(1L).nombre("Local 1").build();
        when(localService.obtenerLocalPorId(1L)).thenReturn(response);

        ResponseEntity<LocalResponse> resultado = localController.obtenerLocal(1L);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertSame(response, resultado.getBody());
        verify(localService).obtenerLocalPorId(1L);
    }

    @Test
    void crearLocal_debeDelegarElRegistro() {
        LocalRequest request = new LocalRequest(10L, "Local", "101", "A", "Ropa", true, 20L);
        LocalResponse response = LocalResponse.builder().id(1L).nombre("Local").build();
        when(localService.registrarLocal(request)).thenReturn(response);

        ResponseEntity<LocalResponse> resultado = localController.crearLocal(request);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertSame(response, resultado.getBody());
        verify(localService).registrarLocal(request);
    }

    @Test
    void actualizarLocal_debeActualizarSoloInformacion() {
        LocalUpdateRequest request = new LocalUpdateRequest("Nuevo nombre", "Calzado");
        LocalResponse response = LocalResponse.builder()
                .id(1L).nombre("Nuevo nombre").giroDeNegocio("Calzado").build();
        when(localService.actualizarLocal(1L, request)).thenReturn(response);

        ResponseEntity<LocalResponse> resultado = localController.actualizarLocal(1L, request);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertSame(response, resultado.getBody());
        verify(localService).actualizarLocal(1L, request);
    }

    @Test
    void desactivarLocal_debeRealizarEliminacionLogica() {
        ResponseEntity<Void> resultado = localController.desactivarLocal(1L);

        assertEquals(HttpStatus.NO_CONTENT, resultado.getStatusCode());
        verify(localService).desactivarLocal(1L);
    }
}
