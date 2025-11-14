// src/main/java/com/muebleria/api/services/VentaService.java
package com.muebleria.api.services;


import com.muebleria.api.dto.CotizacionRequestDTO;
import com.muebleria.api.dto.ItemDTO;
import com.muebleria.api.models.*;
// Importa TODOS tus repositorios (con la 'R' mayúscula)
import com.muebleria.api.repositories.CotizacionRepository;
import com.muebleria.api.repositories.MuebleRepository;
import com.muebleria.api.repositories.VarianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    // Inyectamos todos los repositorios que necesitamos
    @Autowired private MuebleRepository muebleRepository;
    @Autowired private VarianteRepository varianteRepository;
    @Autowired private CotizacionRepository cotizacionRepository;

    /**
     * Lógica del Requisito 4: Cotizaciones y Variantes
     */
    public Cotizacion crearCotizacion(CotizacionRequestDTO request) {

        Cotizacion nuevaCotizacion = new Cotizacion();
        nuevaCotizacion.setEstado("PENDIENTE");

        List<ItemCotizacion> items = new ArrayList<>();
        double totalCotizacion = 0.0;

        // 1. Recorremos los items que nos pidió el cliente (DTOs)
        for (ItemDTO itemDto : request.getItems()) {

            Mueble mueble = muebleRepository.findById(itemDto.getMuebleId())
                    .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));

            double precioItem = mueble.getPrecioBase();
            Variante variante = null; // Por defecto es "normal" (sin variante)

            // 2. Lógica de Variante: Si el cliente mandó un varianteId...
            if (itemDto.getVarianteId() != null) {
                // ...la buscamos y sumamos el aumento de precio
                variante = varianteRepository.findById(itemDto.getVarianteId())
                        .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
                precioItem += variante.getAumentoPrecio();
            }

            // 3. Calculamos el total para este item
            double precioCalculado = precioItem * itemDto.getCantidad();
            totalCotizacion += precioCalculado; // Sumamos al total de la cotización

            // 4. Creamos el ItemCotizacion (la entidad de BD)
            ItemCotizacion item = new ItemCotizacion();
            item.setMueble(mueble);
            item.setVariante(variante); // Será null si no se especificó
            item.setCantidad(itemDto.getCantidad());
            item.setPrecioCalculadoItem(precioCalculado);
            item.setCotizacion(nuevaCotizacion); // Lo asociamos a la nueva cotización

            items.add(item);
        }

        // 5. Asignamos el total y la lista de items a la cotización
        nuevaCotizacion.setTotal(totalCotizacion);
        nuevaCotizacion.setItems(items);

        // 6. Guardamos la cotización (y JPA guardará los items en cascada)
        return cotizacionRepository.save(nuevaCotizacion);
    }

    /**
     * Lógica del Requisito 5: Venta y Stock
     */
    public Cotizacion confirmarVenta(Long cotizacionId) {

        Cotizacion cotizacion = cotizacionRepository.findById(cotizacionId)
            .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        // Validamos que no esté ya vendida
        if (!cotizacion.getEstado().equals("PENDIENTE")) {
            throw new RuntimeException("Esta cotización ya fue procesada.");
        }

        // 1. PRIMERA REVISIÓN: Verificamos el stock ANTES de hacer nada
        for (ItemCotizacion item : cotizacion.getItems()) {
            Mueble mueble = item.getMueble();
            if (mueble.getStock() < item.getCantidad()) {
                // ¡Error! Mensaje exacto de la pauta
                throw new RuntimeException("stock insuficiente");
            }
        }

        // 2. SEGUNDA PASADA: Si hay stock de todo, descontamos
        for (ItemCotizacion item : cotizacion.getItems()) {
            Mueble mueble = item.getMueble();
            int nuevoStock = mueble.getStock() - item.getCantidad();
            mueble.setStock(nuevoStock);
            muebleRepository.save(mueble); // Actualizamos el stock en la BD
        }

        // 3. Marcamos la cotización como VENDIDA
        cotizacion.setEstado("VENDIDA");
        return cotizacionRepository.save(cotizacion);
    }
}
