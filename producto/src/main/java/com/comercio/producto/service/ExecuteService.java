package com.comercio.producto.service;

import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.dto.RespuestaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExecuteService {
    ResponseEntity<RespuestaDTO> validateProducts(List<PeticionDTO> productos);
    ResponseEntity<List<ProductoDTO>> executeProducts(List<ProductoDTO> productos);
}
