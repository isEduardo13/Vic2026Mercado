package com.efecto.web.mercado.victoria.utils;

public class StringUtils {
    public static void validarNoVacio(String input, String message) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void validarTamanio(String texto, Integer min, Integer max, String mensaje) {
        validarNoVacio(texto, mensaje);

        if (texto.length() < min || texto.length() > max) {
            throw new IllegalArgumentException(mensaje);
    }
    }

    public static String quitarAcentos(String texto) {
        return texto.toLowerCase()
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u");
    }
}
