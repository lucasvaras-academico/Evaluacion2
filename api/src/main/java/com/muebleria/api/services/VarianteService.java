// src/main/java/com/muebleria/api/services/VarianteService.java
package com.muebleria.api.services;

import com.muebleria.api.models.Variante;
import com.muebleria.api.repositories.VarianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    public List<Variante> listarVariantes() {
        return varianteRepository.findAll();
    }

    public Variante crearVariante(Variante variante) {
        return varianteRepository.save(variante);
    }
}

