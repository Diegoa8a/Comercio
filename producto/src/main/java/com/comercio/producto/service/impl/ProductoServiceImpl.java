package com.comercio.producto.service.impl;

import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.exception.CategoriaNotFoundException;
import com.comercio.producto.exception.ProductoNotFoundException;
import com.comercio.producto.model.ProductoEntity;
import com.comercio.producto.model.CategoriaEntity;
import com.comercio.producto.repository.ProductoRepository;
import com.comercio.producto.repository.CategoriaRepository;
import com.comercio.producto.service.ProductoService;
import com.comercio.producto.util.ProductoBuilderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ResponseEntity<ProductoDTO> getProductoById(Long id) {
        ProductoEntity productoEntity = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto not found"));
        return productoEntity == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(ProductoBuilderUtil.convertToDTO(productoEntity));
    }


    @Override
    public ResponseEntity<ProductoDTO> createProducto(ProductoDTO productoDTO) {
        CategoriaEntity categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria not found"));
        ProductoEntity producto = ProductoBuilderUtil.convertToEntity(productoDTO, categoria);
        producto = productoRepository.save(producto);
        return ResponseEntity.ok(ProductoBuilderUtil.convertToDTO(producto));
    }

    @Override
    public ResponseEntity<ProductoDTO> updateProducto(ProductoDTO productoDTO) {
        ProductoEntity productoEntity = productoRepository.findByNombre(productoDTO.getNombre());
        Optional.ofNullable(productoEntity).orElseThrow(() -> new ProductoNotFoundException("Producto not found"));
        CategoriaEntity categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria not found"));
        ProductoEntity producto = ProductoBuilderUtil.convertToEntity(productoDTO, categoria);
        producto = productoRepository.save(producto);
        return ResponseEntity.ok(ProductoBuilderUtil.convertToDTO(producto));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteProducto(String nombre) {
        ProductoEntity productoEntity = productoRepository.findByNombre(nombre);
        Optional.ofNullable(productoEntity).orElseThrow(() -> new ProductoNotFoundException("Producto not found"));
        productoRepository.deleteByNombre(nombre);
        return ResponseEntity.ok().build();
    }

}
