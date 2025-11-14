// src/main/java/com/muebleria/api/models/Mueble.java
package com.muebleria.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Mueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMueble;

    private String nombreMueble;
    private String tipo;
    private Double precioBase;
    private Integer stock;
    private String estado; // "activo", "inactivo"
    private String tamanio; // "Grande", "Mediano", "Pequeño"
    private String material;
}