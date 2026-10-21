package com.efecto.web.mercado.victoria.entities;

import org.junit.jupiter.api.Test;
import  static  org.junit.jupiter.api.Assertions.*;



public class LocatarioTest {

    @Test
    void actualizar_debeActualizarNombreYTelefono_cuandoNombreYTelefonoSeanValidos(){

        Locatario locatario = Locatario.builder().nombre("ed").telefono("2128432945").build();

        locatario.actualizar("Carlos Manuel","9394053022");

        assertEquals("Carlos Manuel",locatario.getNombre());
        assertEquals("9394053022",locatario.getTelefono());

    }
    @Test
    void actualizar_debeActualizarNombreYTelefono_cuandoNombreSeaValidoYTelefonoSeaNull(){
        Locatario locatario = Locatario.builder().nombre("ed").telefono("2128432945").build();


        locatario.actualizar("Carlos Manuel", null);

        assertEquals("Carlos Manuel",locatario.getNombre());
        assertEquals(null ,locatario.getTelefono());

    }

    @Test
    void actualizar_debeLanzarExcecion_cuandoNombreSeaNull(){
        Locatario locatario = Locatario.builder().nombre("Carlos Manuel").telefono("2128432945").build();

        Exception exception = assertThrows(IllegalArgumentException.class, ()->locatario.actualizar(null, "2128432945"));

      assertTrue(exception.getMessage().equals("El nombre del locatario no puede estar vacío."));

    }

    @Test
    void actualizar_debeLanzarExcecion_cuandoNombreExedaCaracteres(){
        Locatario locatario = Locatario.builder().nombre("Carlos Manuel").telefono("2128432945").build();
        String nuevoNombre = "A".repeat(151);
        Exception exception = assertThrows(IllegalArgumentException.class, ()->locatario.actualizar(nuevoNombre, "2128432945"));

        assertTrue(exception.getMessage().equals("El nombre del locatario no puede exceder los 150 caracteres."));

    }

    @Test
    void actualizar_debeLanzarExcecion_cuandoTelefonoExedaCaracteres(){
        Locatario locatario = Locatario.builder().nombre("Carlos Manuel").telefono("2128432945").build();
        String nuevoTelefono = "A".repeat(21);
        Exception exception = assertThrows(IllegalArgumentException.class, ()->locatario.actualizar("Manuel", nuevoTelefono));

        assertTrue(exception.getMessage().equals("El teléfono del locatario no puede exceder los 20 caracteres."));

    }


}
