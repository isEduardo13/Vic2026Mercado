package com.efecto.web.mercado.victoria.mappers;

import com.efecto.web.mercado.victoria.dto.locatario.LocatarioRequest;
import com.efecto.web.mercado.victoria.dto.locatario.LocatarioResponse;
import com.efecto.web.mercado.victoria.entities.Locatario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocatarioMapperTest {
    private final LocatarioMapper locatarioMapper = new LocatarioMapper();

    @Test
    void requestToEntity_debeMapearRequestACuandoDatosSonValidos() {
        LocatarioRequest request = new LocatarioRequest("  Juanito Jose  ", "+52-1234567890");

        Locatario resultado = locatarioMapper.requestToEntity(request);

        assertNotNull(resultado);
        assertEquals("Juanito Jose", resultado.getNombre());
        assertEquals("+52-1234567890", resultado.getTelefono());
    }

    @Test
    void requestToEntity_debeRetornarNull_cuandoRequestEsNull() {
        Locatario resultado = locatarioMapper.requestToEntity(null);

        assertNull(resultado);
    }

    @Test
    void requestToEntity_debeTrimNombreYTelefono_cuandoHayEspacios() {
        LocatarioRequest request = new LocatarioRequest("  Ana   ", "   +52-1234567890   ");

        Locatario resultado = locatarioMapper.requestToEntity(request);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
        assertEquals("+52-1234567890", resultado.getTelefono());
    }

    @Test
    void requestToEntity_debeRetornarTelefonoNull_cuandoTelefonoEsVacio() {
        LocatarioRequest request = new LocatarioRequest("Ana", "   ");

        Locatario resultado = locatarioMapper.requestToEntity(request);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNombre());
        assertNull(resultado.getTelefono());
    }

    @Test
    void entityToResponse_debeMapearEntityACuandoExiste() {
        Locatario locatario = Locatario.builder()
                .id(1L)
                .nombre("Juanito Jose")
                .telefono("+52-1234567890")
                .build();

        LocatarioResponse resultado = locatarioMapper.entityToResponse(locatario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.id());
        assertEquals("Juanito Jose", resultado.nombre());
        assertEquals("+52-1234567890", resultado.telefono());
    }

    @Test
    void entityToResponse_debeRetornarNull_cuandoEntityEsNull() {
        LocatarioResponse resultado = locatarioMapper.entityToResponse(null);

        assertNull(resultado);
    }
}
