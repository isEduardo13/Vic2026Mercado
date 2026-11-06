package com.efecto.web.mercado.victoria.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocalTest {

    @Test
    void actualizarUbicacion_debeActualizarNumeroYPasillo_cuandoLosDatosSeanValidos() {
        Local local = Local.builder()
                .numero("101")
                .pasillo("A")
                .nombre("Tienda de ropa")
                .giroDeNegocio("Ropa")
                .esActivo(true)
                .build();

        local.actualizarUbicacion("205", "B");

        assertEquals("205", local.getNumero());
        assertEquals("B", local.getPasillo());
    }

    @Test
    void actualizarDatos_debeActualizarNombreYGiro_cuandoLosDatosSeanValidos() {
        Local local = Local.builder()
                .numero("101")
                .pasillo("A")
                .nombre("Tienda")
                .giroDeNegocio("Ropa")
                .esActivo(true)
                .build();

        local.actualizarDatos("Papelería", "Papelería y oficina");

        assertEquals("Papelería", local.getNombre());
        assertEquals("Papelería y oficina", local.getGiroDeNegocio());
    }

    @Test
    void actualizarEstado_debeActualizarElEstadoDelLocal() {
        Local local = Local.builder()
                .numero("101")
                .pasillo("A")
                .nombre("Tienda")
                .giroDeNegocio("Ropa")
                .esActivo(false)
                .build();

        local.actualizarEstado(true);

        assertTrue(local.getEsActivo());
    }

    @Test
    void actualizarLocalPadre_debeActualizarElIdDelLocalPadre() {
        Local local = Local.builder()
                .numero("101")
                .pasillo("A")
                .nombre("Tienda")
                .giroDeNegocio("Ropa")
                .esActivo(true)
                .build();

        local.actualizarLocalPadre(77L);

        assertEquals(77L, local.getLocalPadreId());
    }

    @Test
    void actualizarUbicacion_debeLanzarExcepcion_cuandoNumeroEsNull() {
        Local local = Local.builder()
                .numero("101")
                .pasillo("A")
                .nombre("Tienda")
                .giroDeNegocio("Ropa")
                .esActivo(true)
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> local.actualizarUbicacion(null, "B"));

        assertEquals("El número del local no puede estar vacío.", exception.getMessage());
    }

    @Test
    void actualizarUbicacion_debeLanzarExcepcion_cuandoPasilloEsVacio() {
        Local local = Local.builder()
                .numero("101")
                .pasillo("A")
                .nombre("Tienda")
                .giroDeNegocio("Ropa")
                .esActivo(true)
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> local.actualizarUbicacion("205", "   "));

        assertEquals("El pasillo no puede estar vacío.", exception.getMessage());
    }

    @Test
    void actualizarDatos_debeLanzarExcepcion_cuandoNombreEsNull() {
        Local local = Local.builder()
                .numero("101")
                .pasillo("A")
                .nombre("Tienda")
                .giroDeNegocio("Ropa")
                .esActivo(true)
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> local.actualizarDatos(null, "Papelería"));

        assertEquals("El nombre del local no puede estar vacío.", exception.getMessage());
    }

    @Test
    void actualizarDatos_debeLanzarExcepcion_cuandoGiroDeNegocioEsVacio() {
        Local local = Local.builder()
                .numero("101")
                .pasillo("A")
                .nombre("Tienda")
                .giroDeNegocio("Ropa")
                .esActivo(true)
                .build();

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> local.actualizarDatos("Tienda", ""));

        assertEquals("El giro de negocio no puede estar vacío.", exception.getMessage());
    }
}
