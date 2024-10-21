package com.comercio.producto.service.impl;


import com.comercio.producto.TestUtil;
import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.exception.CategoriaNotFoundException;
import com.comercio.producto.exception.ProductoNotFoundException;
import com.comercio.producto.model.CategoriaEntity;
import com.comercio.producto.model.ProductoEntity;
import com.comercio.producto.repository.CategoriaRepository;
import com.comercio.producto.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductoById_Found() {
        // Setup
        CategoriaEntity categoriaEntity = new CategoriaEntity(1L, "Category1");
        ProductoEntity productoEntity = TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, categoriaEntity);
        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(productoEntity));

        // Execute
        ResponseEntity<ProductoDTO> response = productoService.getProductoById(1L);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product1", response.getBody().getNombre());
    }

    @Test
    void testGetProductoById_NotFound() {
        // Setup
        when(productoRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(ProductoNotFoundException.class, () -> productoService.getProductoById(1L));
    }

    @Test
    void testCreateProducto_CategoryFound() {
        // Setup
        ProductoDTO productoDTO = TestUtil.createProductoDTO(1l, "Product1", 100.0, 10, true, 2l);
        CategoriaEntity categoriaEntity = new CategoriaEntity(2l, "Books");
        when(categoriaRepository.findById(2l)).thenReturn(Optional.of(categoriaEntity));
        when(productoRepository.save(any())).thenReturn(TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, categoriaEntity));

        // Execute
        ResponseEntity<ProductoDTO> response = productoService.createProducto(productoDTO);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product1", response.getBody().getNombre());
    }

    @Test
    void testCreateProducto_CategoryNotFound() {
        // Setup
        ProductoDTO productoDTO = TestUtil.createProductoDTO(1l, "Product1", 100.0, 10, true, 5l);
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(CategoriaNotFoundException.class, () -> productoService.createProducto(productoDTO));
    }

    @Test
    void testUpdateProducto_ProductAndCategoryFound() {
        // Setup
        ProductoDTO productoDTO = TestUtil.createProductoDTO(1l, "Product1", 100.0, 10, true, 2l);
        CategoriaEntity categoriaEntity = new CategoriaEntity(2L, "Books");
        ProductoEntity productoEntity = TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, categoriaEntity);
        when(productoRepository.findByNombre(anyString())).thenReturn(productoEntity);
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoriaEntity));
        when(productoRepository.save(any(ProductoEntity.class))).thenReturn(productoEntity);

        // Execute
        ResponseEntity<ProductoDTO> response = productoService.updateProducto(productoDTO);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Product1", response.getBody().getNombre());
    }

    @Test
    void testUpdateProducto_ProductNotFound() {
        // Setup
        ProductoDTO productoDTO = TestUtil.createProductoDTO(1l, "Product1", 100.0, 10, true, 5l);
        when(productoRepository.findByNombre(anyString())).thenReturn(null);

        // Execute & Verify
        assertThrows(ProductoNotFoundException.class, () -> productoService.updateProducto(productoDTO));
    }

    @Test
    void testUpdateProducto_CategoryNotFound() {
        // Setup
        ProductoDTO productoDTO = TestUtil.createProductoDTO(1l, "Product1", 100.0, 10, true, 5l);
        ProductoEntity productoEntity = TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, new CategoriaEntity(1L, "Category1"));
        when(productoRepository.findByNombre(anyString())).thenReturn(productoEntity);
        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(CategoriaNotFoundException.class, () -> productoService.updateProducto(productoDTO));
    }

    @Test
    void testDeleteProducto_ProductFound() {
        // Setup
        ProductoEntity productoEntity = TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, new CategoriaEntity(1L, "Category1"));
        when(productoRepository.findByNombre(anyString())).thenReturn(productoEntity);

        // Execute
        ResponseEntity<Void> response = productoService.deleteProducto("Product1");

        // Verify
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testDeleteProducto_ProductNotFound() {
        // Setup
        when(productoRepository.findByNombre(anyString())).thenReturn(null);

        // Execute & Verify
        assertThrows(ProductoNotFoundException.class, () -> productoService.deleteProducto("Product1"));
    }
}