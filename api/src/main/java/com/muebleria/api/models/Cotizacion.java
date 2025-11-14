// src/main/java/com/muebleria/api/models/Cotizacion.java
package com.muebleria.api.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double total;
    private String estado; // "PENDIENTE" o "VENDIDA"

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemCotizacion> items;
}