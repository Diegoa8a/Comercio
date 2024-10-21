package com.comercio.producto.builder;

import com.comercio.producto.dto.ProductoDTO;

public class ProductoDTOBuilder {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Boolean activo;
    private Long categoriaId;

    public ProductoDTOBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductoDTOBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoDTOBuilder setPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public ProductoDTOBuilder setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public ProductoDTOBuilder setActivo(Boolean activo) {
        this.activo = activo;
        return this;
    }

    public ProductoDTOBuilder setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
        return this;
    }

    public ProductoDTO build() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(this.id);
        productoDTO.setNombre(this.nombre);
        productoDTO.setPrecio(this.precio);
        productoDTO.setStock(this.stock);
        productoDTO.setActivo(this.activo);
        productoDTO.setCategoriaId(this.categoriaId);
        return productoDTO;
    }
}
