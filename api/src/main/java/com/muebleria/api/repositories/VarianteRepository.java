// src/main/java/com/muebleria/api/repositories/VarianteRepository.java
package com.muebleria.api.repositories;

import com.muebleria.api.models.Variante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarianteRepository extends JpaRepository<Variante, Long> {
}
