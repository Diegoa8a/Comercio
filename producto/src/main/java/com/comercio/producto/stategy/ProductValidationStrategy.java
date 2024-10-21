package com.comercio.producto.stategy;

import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.RespuestaDTO;
import com.comercio.producto.model.ProductoEntity;

public interface ProductValidationStrategy {
   RespuestaDTO validationPeticionDTO(ProductoEntity productoEntity, PeticionDTO peticionDTO, RespuestaDTO respuestaDTO);
}
