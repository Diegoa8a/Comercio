package com.comercio.producto;

import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.dto.RespuestaDTO;
import com.comercio.producto.model.CategoriaEntity;
import com.comercio.producto.model.ProductoEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestUtil {

    public static PeticionDTO createPeticionDTO(Long id, Integer cantidad) {
        PeticionDTO peticionDTO = new PeticionDTO();
        peticionDTO.setId(id);
        peticionDTO.setStock(cantidad);
        return peticionDTO;
    }

    public static ProductoDTO createProductoDTO(Long id, String nombre, Double precio, Integer stock, Boolean activo, Long categoriaId) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(id);
        productoDTO.setNombre(nombre);
        productoDTO.setPrecio(precio);
        productoDTO.setStock(stock);
        productoDTO.setActivo(activo);
        productoDTO.setCategoriaId(categoriaId);
        return productoDTO;
    }

    public static RespuestaDTO createRespuestaDTO(List<String> errores) {
        RespuestaDTO respuestaDTO = new RespuestaDTO(new ArrayList<>(), new ArrayList<>());
        respuestaDTO.setErrores(errores);
        return respuestaDTO;
    }

    public static RespuestaDTO createEmptyRespuestaDTO() {
        return createRespuestaDTO(Collections.emptyList());
    }
    public static ProductoEntity createProductoEntity(Long id, String nombre, Double aDouble, Boolean activo, Integer stock, CategoriaEntity category1) {
        ProductoEntity productoEntity = new ProductoEntity();
        productoEntity.setId(id);
        productoEntity.setNombre(nombre);
        productoEntity.setPrecio(aDouble);
        productoEntity.setActivo(activo);
        productoEntity.setStock(stock);
        productoEntity.setCategoria(category1);
        return productoEntity;
    }
}
