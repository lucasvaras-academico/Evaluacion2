// src/main/java/com/muebleria/api/repositories/ItemCotizacionRepository.java
package com.muebleria.api.repositories;

import com.muebleria.api.models.ItemCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCotizacionRepository extends JpaRepository<ItemCotizacion, Long> {
}

