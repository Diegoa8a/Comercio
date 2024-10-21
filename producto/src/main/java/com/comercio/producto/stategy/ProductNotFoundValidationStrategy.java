package com.comercio.producto.stategy;

import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.RespuestaDTO;
import com.comercio.producto.model.ProductoEntity;

public class ProductNotFoundValidationStrategy implements ProductValidationStrategy{
    @Override
    public RespuestaDTO validationPeticionDTO(ProductoEntity productoEntity, PeticionDTO peticionDTO, RespuestaDTO respuestaDTO) {
        if (productoEntity != null) {
            return respuestaDTO;
        }
        respuestaDTO.getErrores().add("Producto no encontrado: " + peticionDTO.getId());
        return respuestaDTO;
    }
}
