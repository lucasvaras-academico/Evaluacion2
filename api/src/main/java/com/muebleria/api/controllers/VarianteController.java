// src/main/java/com/muebleria/api/controllers/VarianteController.java
package com.muebleria.api.controllers;

import com.muebleria.api.models.Variante;
import com.muebleria.api.services.VarianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VarianteController {

    @Autowired
    private VarianteService varianteService;

    @PostMapping
    public Variante crearVariante(@RequestBody Variante variante) {
        return varianteService.crearVariante(variante);
    }

    @GetMapping
    public List<Variante> listarVariantes() {
        return varianteService.listarVariantes();
    }
}
