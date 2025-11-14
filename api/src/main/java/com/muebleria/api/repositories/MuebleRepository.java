// src/main/java/com/muebleria/api/repositories/MuebleRepository.java
package com.muebleria.api.repositories;

import com.muebleria.api.models.Mueble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuebleRepository extends JpaRepository<Mueble, Long> {
}

