package com.efecto.web.mercado.victoria.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {
    @Test
    void validarNoVacio_debeNoLanzarExcepcion_cuandoTextoTieneContenido() {
        assertDoesNotThrow(() -> StringUtils.validarNoVacio("  Juan  ", "El texto es obligatorio"));
    }

    @Test
    void validarNoVacio_debeLanzarExcepcion_cuandoTextoEsNuloOBlanco() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> StringUtils.validarNoVacio("   ", "El texto es obligatorio")
        );
        assertEquals("El texto es obligatorio", exception.getMessage());

        IllegalArgumentException exceptionNull = assertThrows(
                IllegalArgumentException.class,
                () -> StringUtils.validarNoVacio(null, "El texto es obligatorio")
        );
        assertEquals("El texto es obligatorio", exceptionNull.getMessage());
    }
    @Test
    void validarTamanio_debeNoLanzarExcepcion_cuandoTextoEstaDentroDelRango() {
        assertDoesNotThrow(() -> StringUtils.validarTamanio("hola", 2, 10, "El tamaño es inválido"));
    }

    @Test
    void validarTamanio_debeLanzarExcepcion_cuandoTextoFueraDelRango() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> StringUtils.validarTamanio("hola", 1, 3, "El tamaño es inválido")
        );
        assertEquals("El tamaño es inválido", exception.getMessage());
    }

    @Test
    void quitarAcentos_debeEliminarAcentosYConvertirAMinusculas() {
        assertEquals("juan jose", StringUtils.quitarAcentos("Juán José"));
    }

}
