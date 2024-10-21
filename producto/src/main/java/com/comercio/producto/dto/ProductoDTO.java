package com.comercio.producto.dto;

import lombok.Data;
@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Boolean activo;
    private Long categoriaId;
}
