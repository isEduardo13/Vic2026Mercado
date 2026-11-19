package com.efecto.web.mercado.victoria.mappers;

import com.efecto.web.mercado.victoria.dto.local.LocalRequest;
import com.efecto.web.mercado.victoria.dto.local.LocalResponse;
import com.efecto.web.mercado.victoria.entities.Local;
import com.efecto.web.mercado.victoria.entities.Locatario;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LocalMapperTest {

    private final LocalMapper mapper = new LocalMapper();

    @Test
    void requestToEntity_debeMapearLosDatosDelRequest() {
        LocalRequest request = new LocalRequest(10L, " Local central ", " 101 ", " A ", " Ropa ", true, 20L);

        Local resultado = mapper.requestToEntity(request);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getLocalPadreId());
        assertEquals("Local central", resultado.getNombre());
        assertEquals("101", resultado.getNumero());
        assertEquals("A", resultado.getPasillo());
        assertEquals("Ropa", resultado.getGiroDeNegocio());
        assertTrue(resultado.getEsActivo());
        assertEquals(20L, resultado.getLocatario().getId());
        assertNotNull(resultado.getCreadoEl());
    }

    @Test
    void requestToEntity_debeUsarElLocatarioRecibido() {
        LocalRequest request = new LocalRequest(null, "Local", "1", "B", "Calzado", false, 20L);
        Locatario locatario = Locatario.builder().id(20L).nombre("Ana").build();

        Local resultado = mapper.requestToEntity(request, locatario);

        assertSame(locatario, resultado.getLocatario());
    }

    @Test
    void requestToEntity_debeRetornarNullCuandoRequestEsNull() {
        assertNull(mapper.requestToEntity(null));
    }

    @Test
    void entityToResponse_debeMapearLaEntidad() {
        Locatario locatario = Locatario.builder().id(20L).nombre("Ana").build();
        Local local = Local.builder()
                .id(5L)
                .localPadreId(10L)
                .nombre("Local central")
                .numero("101")
                .pasillo("A")
                .giroDeNegocio("Ropa")
                .esActivo(true)
                .creadoEl(LocalDateTime.of(2026, 11, 18, 10, 30))
                .locatario(locatario)
                .build();

        LocalResponse resultado = mapper.entityToResponse(local);

        assertEquals(5L, resultado.id());
        assertEquals(10L, resultado.localPadreId());
        assertEquals("Local central", resultado.nombre());
        assertEquals("101", resultado.numero());
        assertEquals("A", resultado.pasillo());
        assertEquals("Ropa", resultado.giroDeNegocio());
        assertTrue(resultado.esActivo());
        assertEquals(20L, resultado.locatarioId());
    }

    @Test
    void entityToResponse_debeRetornarNullCuandoEntidadEsNull() {
        assertNull(mapper.entityToResponse(null));
    }
}
