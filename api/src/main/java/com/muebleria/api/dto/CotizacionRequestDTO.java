// src/main/java/com/muebleria/api/dto/CotizacionRequestDTO.java
package com.muebleria.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class CotizacionRequestDTO {
    private List<ItemDTO> items;
}

