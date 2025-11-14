// src/main/java/com/muebleria/api/repositories/CotizacionRepository.java
package com.muebleria.api.repositories;

import com.muebleria.api.models.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {
}

