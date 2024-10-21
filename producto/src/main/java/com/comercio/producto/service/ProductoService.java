package com.comercio.producto.service;
import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductoService {
    ResponseEntity<ProductoDTO> getProductoById(Long id);
    ResponseEntity<ProductoDTO> createProducto(ProductoDTO productoDTO);
    ResponseEntity<ProductoDTO> updateProducto(ProductoDTO productoDTO);
    ResponseEntity<Void> deleteProducto(String nombre);
}
