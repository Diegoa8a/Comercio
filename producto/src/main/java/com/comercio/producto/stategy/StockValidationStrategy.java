package com.comercio.producto.stategy;

import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.RespuestaDTO;
import com.comercio.producto.model.ProductoEntity;
import com.comercio.producto.util.ProductoBuilderUtil;

public class StockValidationStrategy implements ProductValidationStrategy{
    @Override
    public RespuestaDTO validationPeticionDTO(ProductoEntity productoEntity, PeticionDTO peticionDTO, RespuestaDTO respuestaDTO) {

        if (productoEntity == null) return respuestaDTO;

        if (productoEntity.getStock() >= peticionDTO.getStock()) {
            respuestaDTO.getProductos().add(ProductoBuilderUtil.convertToDTO(productoEntity));
            return respuestaDTO;
        }
        respuestaDTO.getErrores().add("Stock insuficiente: " + peticionDTO.getId());
        return respuestaDTO;
    }
}
