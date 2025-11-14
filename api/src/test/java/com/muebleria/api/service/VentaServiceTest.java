// src/test/java/com/muebleria/api/services/VentaServiceTest.java
package com.muebleria.api.service;

import com.muebleria.api.models.Cotizacion;
import com.muebleria.api.models.ItemCotizacion;
import com.muebleria.api.models.Mueble;
import com.muebleria.api.models.Variante;

// Asegúrate de importar tus repositorios (con R mayúscula o minúscula)
import com.muebleria.api.repositories.CotizacionRepository;
import com.muebleria.api.repositories.MuebleRepository;
import com.muebleria.api.services.VentaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // <-- Habilita Mockito
public class VentaServiceTest {

    @Mock // Simula el repositorio de Muebles (no usa la BD real)
    private MuebleRepository muebleRepository;

    @Mock // Simula el repositorio de Cotizaciones
    private CotizacionRepository cotizacionRepository;

    @InjectMocks // Inyecta los Mocks (@Mock) de arriba en nuestro servicio
    private VentaService ventaService;

    // Test Requisito 7: Qué sucede si hay una venta con stock insuficiente
    @Test
    void testConfirmarVenta_FallaPorStockInsuficiente() {
        // 1. ARRANGE (Preparar los datos simulados)
        Mueble silla = new Mueble();
        silla.setIdMueble(1L);
        silla.setStock(5); // Solo hay 5

        ItemCotizacion item = new ItemCotizacion();
        item.setMueble(silla);
        item.setCantidad(10); // Queremos 10

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(1L);
        cotizacion.setEstado("PENDIENTE");
        cotizacion.setItems(List.of(item));

        // Cuando el servicio pregunte por la cotización 1L, le devolvemos la nuestra
        when(cotizacionRepository.findById(1L)).thenReturn(Optional.of(cotizacion));

        // 2. ACT & ASSERT (Actuar y Afirmar)
        // Verificamos que al llamar al método, se lanza la excepción correcta
        Exception exception = assertThrows(RuntimeException.class, () -> {
            ventaService.confirmarVenta(1L);
        });

        // Verificamos que el mensaje es el pedido en la pauta
        assertEquals("stock insuficiente", exception.getMessage());
        
        // Verificamos que NUNCA se llamó a muebleRepository.save()
        verify(muebleRepository, never()).save(any(Mueble.class));
    }

    // Test Requisito 7: Qué sucede si se confirma una venta (exitosa)
    @Test
    void testConfirmarVenta_Exito() {
        // 1. ARRANGE
        Mueble mesa = new Mueble();
        mesa.setIdMueble(1L);
        mesa.setStock(20); // Hay 20

        ItemCotizacion item = new ItemCotizacion();
        item.setMueble(mesa);
        item.setCantidad(5); // Queremos 5

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(1L);
        cotizacion.setEstado("PENDIENTE");
        cotizacion.setItems(List.of(item));

        when(cotizacionRepository.findById(1L)).thenReturn(Optional.of(cotizacion));
        // Cuando se guarde la cotización, solo devuélvela (comportamiento simulado)
        when(cotizacionRepository.save(any(Cotizacion.class))).thenAnswer(i -> i.getArgument(0));

        // 2. ACT
        Cotizacion cotizacionVendida = ventaService.confirmarVenta(1L);

        // 3. ASSERT
        // Verificamos que el stock se descontó
        assertEquals(15, mesa.getStock()); 
        // Verificamos que el estado de la cotización cambió
        assertEquals("VENDIDA", cotizacionVendida.getEstado());
        // Verificamos que SÍ se guardó el mueble (1 vez)
        verify(muebleRepository, times(1)).save(mesa);
        // Verificamos que SÍ se guardó la cotización
        verify(cotizacionRepository, times(1)).save(cotizacion);
    }
}


