// src/main/java/com/muebleria/api/dto/ItemDTO.java
package com.muebleria.api.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private Long muebleId;
    private Long varianteId; // null si es "normal"
    private Integer cantidad;
}

