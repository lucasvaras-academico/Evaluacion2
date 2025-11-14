// src/main/java/com/muebleria/api/controllers/CotizacionController.java
package com.muebleria.api.controllers;

import com.muebleria.api.dto.CotizacionRequestDTO;
import com.muebleria.api.models.Cotizacion;
import com.muebleria.api.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    @Autowired
    private VentaService ventaService;

    // Endpoint para CREAR una cotización
    @PostMapping
    public Cotizacion crearNuevaCotizacion(@RequestBody CotizacionRequestDTO request) {
        return ventaService.crearCotizacion(request);
    }

    // Endpoint para CONFIRMAR una cotización como VENTA
    @PostMapping("/{idCotizacion}/confirmar-venta")
    public Cotizacion confirmarVenta(@PathVariable Long idCotizacion) {
        return ventaService.confirmarVenta(idCotizacion);
    }
}

