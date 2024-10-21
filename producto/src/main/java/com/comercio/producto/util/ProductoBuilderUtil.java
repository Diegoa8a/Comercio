package com.comercio.producto.util;

import com.comercio.producto.builder.ProductoDTOBuilder;
import com.comercio.producto.builder.ProductoEntityBuilder;
import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.model.ProductoEntity;
import com.comercio.producto.model.CategoriaEntity;

public class ProductoBuilderUtil {

    public static ProductoDTO convertToDTO(ProductoEntity producto) {
        return new ProductoDTOBuilder()
                .setId(producto.getId())
                .setNombre(producto.getNombre())
                .setPrecio(producto.getPrecio())
                .setStock(producto.getStock())
                .setActivo(producto.getActivo())
                .setCategoriaId(producto.getCategoria().getId())
                .build();
    }

    public static ProductoEntity convertToEntity(ProductoDTO productoDTO, CategoriaEntity categoria) {
        return new ProductoEntityBuilder()
                .setId(productoDTO.getId())
                .setNombre(productoDTO.getNombre())
                .setPrecio(productoDTO.getPrecio())
                .setStock(productoDTO.getStock())
                .setActivo(productoDTO.getActivo())
                .setCategoria(categoria)
                .build();
    }
}
