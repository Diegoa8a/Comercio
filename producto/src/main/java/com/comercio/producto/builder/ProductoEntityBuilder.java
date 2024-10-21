package com.comercio.producto.builder;

import com.comercio.producto.model.CategoriaEntity;
import com.comercio.producto.model.ProductoEntity;

public class ProductoEntityBuilder {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private Boolean activo;
    private CategoriaEntity categoria;

    public ProductoEntityBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductoEntityBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ProductoEntityBuilder setPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public ProductoEntityBuilder setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public ProductoEntityBuilder setActivo(Boolean activo) {
        this.activo = activo;
        return this;
    }

    public ProductoEntityBuilder setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
        return this;
    }

    public ProductoEntity build() {
        ProductoEntity producto = new ProductoEntity();
        producto.setId(this.id);
        producto.setNombre(this.nombre);
        producto.setPrecio(this.precio);
        producto.setStock(this.stock);
        producto.setActivo(this.activo);
        producto.setCategoria(this.categoria);
        return producto;
    }
}
