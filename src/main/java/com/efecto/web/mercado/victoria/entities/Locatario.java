package com.efecto.web.mercado.victoria.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "locatarios")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter

public class Locatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locatario")
        private Long id;

    @Column(name = "nombre", nullable = false)
    @Size(min = 1, max = 150)
    private String nombre;

    @Column(name = "telefono", nullable = true)
    @Size(max = 20)
    private String telefono;

public void actualizar(String nombre, String telefono) {
    validarDatos(nombre, telefono);
    this.nombre = nombre;
    this.telefono = telefono;
}

private void validarDatos(String nombre, String telefono) {
    if (nombre == null || nombre.trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre del locatario no puede estar vacío.");
    }
    if (nombre.length() > 150) {
        throw new IllegalArgumentException("El nombre del locatario no puede exceder los 150 caracteres.");
    }
    if (telefono != null && telefono.length() > 20) {
        throw new IllegalArgumentException("El teléfono del locatario no puede exceder los 20 caracteres.");
    }
}
}
