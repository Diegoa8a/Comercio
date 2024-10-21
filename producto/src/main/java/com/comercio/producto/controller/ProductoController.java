package com.comercio.producto.controller;

import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoByNombre(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.createProducto(productoDTO);
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> updateProducto(@RequestBody ProductoDTO productoDTO) {
        return productoService.updateProducto(productoDTO);
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<Void> deleteProducto(@PathVariable String nombre) {
        return productoService.deleteProducto(nombre);
    }
}
