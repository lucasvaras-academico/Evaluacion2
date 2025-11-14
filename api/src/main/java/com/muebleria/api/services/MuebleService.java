// src/main/java/com/muebleria/api/services/MuebleService.java
package com.muebleria.api.services;

import com.muebleria.api.models.Mueble;
// Asegúrate de que el 'import' coincida con tu carpeta (Repositories con R mayúscula)
import com.muebleria.api.repositories.MuebleRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // <-- Importante: Marca esta clase como un Servicio
public class MuebleService {

    @Autowired // <-- Importante: Inyecta el repositorio
    private MuebleRepository muebleRepository;

    public List<Mueble> listarMuebles() {
        return muebleRepository.findAll();
    }

    public Mueble crearMueble(Mueble mueble) {
        mueble.setEstado("activo"); // Estado por defecto al crear
        return muebleRepository.save(mueble);
    }

    public Mueble actualizarMueble(Long id, Mueble muebleActualizado) {
        // Se asegura de que estamos actualizando el mueble con el ID correcto
        muebleActualizado.setIdMueble(id); 
        return muebleRepository.save(muebleActualizado);
    }

    public Mueble desactivarMueble(Long id) {
        // Busca el mueble, y si no lo encuentra, lanza un error
        Mueble mueble = muebleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));

        mueble.setEstado("inactivo"); // Cambia el estado
        return muebleRepository.save(mueble); // Guarda el cambio
    }
}
