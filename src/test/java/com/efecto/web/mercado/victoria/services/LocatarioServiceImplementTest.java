package com.efecto.web.mercado.victoria.services;


import com.efecto.web.mercado.victoria.dto.locatario.LocatarioResponse;
import com.efecto.web.mercado.victoria.entities.Locatario;
import com.efecto.web.mercado.victoria.exception.RecursoNoEncontradoException;
import com.efecto.web.mercado.victoria.mappers.LocatarioMapper;
import com.efecto.web.mercado.victoria.repositories.LocatarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class LocatarioServiceImplementTest {

    @Mock
    private LocatarioRepository locatarioRepository;

    @Mock
    private LocatarioMapper locatarioMapper;

    @InjectMocks
    private LocatarioServiceImplement locatarioService;

    private Locatario locatario;

    private LocatarioResponse locatarioResponse;

    @BeforeEach
    void setUp() {
        locatario = Locatario.builder()
                .id(1L)
                .nombre("Juanito Jose")
                .telefono("2444567854")
                .build();
        locatarioResponse = LocatarioResponse.builder().id(1L).nombre("Juanito Jose").telefono("2444567854").build();
    }
    @Test
    void obtenerLocatarios_debeRetornarListaDeLocatarios_cuandoExistenRegistros(){
        when(locatarioRepository.findAll()).thenReturn(List.of(locatario));
        when(locatarioMapper.entityToResponse(locatario)).thenReturn(locatarioResponse);

        List<LocatarioResponse> resultado = locatarioService.obtenerLocatarios();

        assertEquals(1, resultado.size());
        assertEquals("Juanito Jose", locatario.getNombre());
        verify(locatarioRepository, times(1)).findAll();
    }

    @Test
    void obtenerLocatarios_debeRetornarListaVacia_cuandoNoExistenRegistros(){
        when(locatarioRepository.findAll()).thenReturn(List.of());

        List<LocatarioResponse> resultado = locatarioService.obtenerLocatarios();


        assertEquals(0, resultado.size());
    }

    @Test
    void obtenerPorId_debeRetornarLocatario_cuandoExiste(){
        when(locatarioRepository.findById(1L)).thenReturn(Optional.of(locatario));
        when(locatarioMapper.entityToResponse(locatario)).thenReturn(locatarioResponse);

        LocatarioResponse resultado = locatarioService.obtenerLocatario(1L);


        assertEquals(1, resultado.id());
        assertEquals("Juanito Jose", resultado.nombre());
        verify(locatarioRepository, times(1)).findById(1L);
    }
    @Test
    void obtenerPorId_debeLanzarExcepcion_cuandoNoExiste(){
        when(locatarioRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RecursoNoEncontradoException.class, () -> locatarioService.obtenerLocatario(1L));

        assertEquals("Locatario no encontrado", exception.getMessage());
        verify(locatarioRepository, times(1)).findById(1L);


    }





}
