package com.comercio.producto.service.impl;


import com.comercio.producto.TestUtil;
import com.comercio.producto.dto.PeticionDTO;
import com.comercio.producto.dto.ProductoDTO;
import com.comercio.producto.dto.RespuestaDTO;
import com.comercio.producto.model.CategoriaEntity;
import com.comercio.producto.model.ProductoEntity;
import com.comercio.producto.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class ExecuteServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ExecuteServiceImpl executeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testValidateProducts_AllValid() {
        // Setup
        List<PeticionDTO> peticionDTOs = Arrays.asList(TestUtil.createPeticionDTO(1L, 5),
                                                       TestUtil.createPeticionDTO(2L, 5));
        CategoriaEntity categoriaEntity = new CategoriaEntity(1L, "Category1");
        ProductoEntity product1 = TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, categoriaEntity);
        ProductoEntity product2 = TestUtil.createProductoEntity(2L, "Product2",100.00 ,true, 10, categoriaEntity);
        when(productoRepository.findByIdIn(anyList())).thenReturn(Arrays.asList(product1, product2));

        // Execute
        ResponseEntity<RespuestaDTO> response = executeService.validateProducts(peticionDTOs);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, response.getBody().getErrores().size());
    }

    @Test
    void testValidateProducts_ProductNotFound() {
        // Setup
        List<PeticionDTO> peticionDTOs = Arrays.asList(TestUtil.createPeticionDTO(1L, 5),
                                                       TestUtil.createPeticionDTO(3L, 5));

        CategoriaEntity categoriaEntity = new CategoriaEntity(1L, "Category1");
        ProductoEntity product1 = TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, categoriaEntity);
        ProductoEntity product2 = TestUtil.createProductoEntity(2L, "Product2",100.00 ,true, 10, categoriaEntity);


        when(productoRepository.findByIdIn(anyList())).thenReturn(Arrays.asList(product1, product2));

        // Execute
        ResponseEntity<RespuestaDTO> response = executeService.validateProducts(peticionDTOs);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getErrores().size());
    }

    @Test
    void testValidateProducts_ProductInactive() {
        // Setup
        List<PeticionDTO> peticionDTOs = Arrays.asList(TestUtil.createPeticionDTO(1L, 5));
        CategoriaEntity categoriaEntity = new CategoriaEntity(1L, "Category1");
        ProductoEntity product1 = TestUtil.createProductoEntity(1L, "Product1",100.00 ,false, 10, categoriaEntity);
        when(productoRepository.findByIdIn(anyList())).thenReturn(Arrays.asList(product1));

        // Execute
        ResponseEntity<RespuestaDTO> response = executeService.validateProducts(peticionDTOs);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getErrores().size());
    }

    @Test
    void testValidateProducts_InsufficientStock() {
        // Setup
        List<PeticionDTO> peticionDTOs = Arrays.asList(TestUtil.createPeticionDTO(1L, 11));
        CategoriaEntity categoriaEntity = new CategoriaEntity(1L, "Category1");
        ProductoEntity product1 = TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, categoriaEntity);
        when(productoRepository.findByIdIn(anyList())).thenReturn(Arrays.asList(product1));

        // Execute
        ResponseEntity<RespuestaDTO> response = executeService.validateProducts(peticionDTOs);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getErrores().size());
    }

    @Test
    void testExecuteProducts_AllUpdated() {
        // Setup
        List<ProductoDTO> productoDTOs = Arrays.asList(TestUtil.createProductoDTO(1L, "Product1", 100.00, 5,true, 1l),
                                                       TestUtil.createProductoDTO(2L, "Product2", 100.00, 5,true, 1l));
        CategoriaEntity categoriaEntity = new CategoriaEntity(1L, "Category1");
        ProductoEntity product1 = TestUtil.createProductoEntity(1L, "Product1",100.00 ,true, 10, categoriaEntity);
        ProductoEntity product2 = TestUtil.createProductoEntity(2L, "Product2",100.00 ,true, 10, categoriaEntity);
        when(productoRepository.findByIdIn(anyList())).thenReturn(Arrays.asList(product1, product2));

        // Execute
        ResponseEntity<List<ProductoDTO>> response = executeService.executeProducts(productoDTOs);

        // Verify
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(5, product1.getStock());
    }
}