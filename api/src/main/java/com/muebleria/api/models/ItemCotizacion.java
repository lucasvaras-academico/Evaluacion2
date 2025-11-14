// src/main/java/com/muebleria/api/models/ItemCotizacion.java
package com.muebleria.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ItemCotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    private Double precioCalculadoItem;

    @ManyToOne
    @JoinColumn(name = "mueble_id")
    private Mueble mueble;

    @ManyToOne(optional = true) 
    @JoinColumn(name = "variante_id", nullable = true)
    private Variante variante;

    @ManyToOne
    @JoinColumn(name = "cotizacion_id")
    private Cotizacion cotizacion;
}
