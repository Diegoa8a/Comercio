package com.comercio.pedidos.dto;

import lombok.Data;

import java.util.List;

@Data
public class RespuestaDTO {
    List<ProductoDTO> productos;
    List<String> errores;

    public RespuestaDTO(List<ProductoDTO> productos, List<String> errores) {
        this.productos = productos;
        this.errores = errores;
    }
}
