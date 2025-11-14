// src/main/java/com/muebleria/api/controllers/MuebleController.java
package com.muebleria.api.controllers;

import com.muebleria.api.models.Mueble;
import com.muebleria.api.services.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/muebles")
public class MuebleController {

    @Autowired
    private MuebleService muebleService;

    // CREAR
    @PostMapping
    public Mueble crearMueble(@RequestBody Mueble mueble) {
        return muebleService.crearMueble(mueble);
    }

    // LISTAR (LEER)
    @GetMapping
    public List<Mueble> listarMuebles() {
        return muebleService.listarMuebles();
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public Mueble actualizarMueble(@PathVariable Long id, @RequestBody Mueble mueble) {
        return muebleService.actualizarMueble(id, mueble);
    }

    // DESACTIVAR (BORRAR LÓGICO)
    @DeleteMapping("/{id}")
    public Mueble desactivarMueble(@PathVariable Long id) {
        return muebleService.desactivarMueble(id);
    }
}

