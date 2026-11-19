package com.efecto.web.mercado.victoria.entities;

import com.efecto.web.mercado.victoria.utils.StringUtils;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "locales")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_de_local")
    private Long id;

    @Column(name = "local_padre_id")
    private Long localPadreId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "numero" ,nullable = false)
    private String numero;

    @Column(name = "pasillo")
    private String pasillo;

    @Column(name = "giro_de_negocio")
    private String giroDeNegocio;

    @Column(name = "es_activo")
    private Boolean esActivo;

    @Column(name = "creado_el")
    private LocalDateTime creadoEl;

    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Locatario locatario;

    public void actualizarUbicacion( String numero, String pasillo){

        validarVacio(pasillo, "El pasillo no puede estar vacío.");
        validarVacio(numero, "El número del local no puede estar vacío.");

        this.numero = numero;
        this.pasillo = pasillo;
    }

    public void validarVacio(String valor, String mensaje){
        StringUtils.validarNoVacio(valor, mensaje);
    }

    public void actualizarDatos(String nombre, String giroDeNegocio){

        validarVacio(nombre, "El nombre del local no puede estar vacío.");
        validarVacio(giroDeNegocio, "El giro de negocio no puede estar vacío.");

        this.nombre = nombre;
        this.giroDeNegocio = giroDeNegocio;

    }
    public void actualizarEstado(Boolean estado){

        this.esActivo = estado;
    }

    public void actualizarLocalPadre(Long id){
        this.localPadreId = id;

    }




}
